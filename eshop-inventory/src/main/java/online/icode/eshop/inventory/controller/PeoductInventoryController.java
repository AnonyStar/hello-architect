package online.icode.eshop.inventory.controller;

import lombok.extern.slf4j.Slf4j;
import online.icode.eshop.inventory.model.ProductInventory;
import online.icode.eshop.inventory.model.vo.Response;
import online.icode.eshop.inventory.request.ProductInventoryCacheRefreshRequest;
import online.icode.eshop.inventory.request.ProductInventoryDBUpdateRequest;
import online.icode.eshop.inventory.request.base.Request;
import online.icode.eshop.inventory.service.ProductInventoryService;
import online.icode.eshop.inventory.service.RequestAsyncProcessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2021/3/7 12:37
 */
@RestController
@RequestMapping("product")
@Slf4j
public class PeoductInventoryController {

    @Resource
    private ProductInventoryService productInventoryService;
    @Resource
    private RequestAsyncProcessService requestAsyncProcessService;

    @PostMapping("update")
    public Response updateProductInventory(@RequestBody ProductInventory inventory) {
        log.info("============= 日志 ============= ：接受到更新商品库存的请求，商品 id = {}", inventory.getProductId());
        //构建更新请求
        final Request request = new ProductInventoryDBUpdateRequest(inventory, productInventoryService);
        requestAsyncProcessService.process(request);
        return Response.ok();
    }

    @GetMapping("get")
    public Response getProductInventory(@RequestParam("productId") Integer productId) {
        log.info("============= 日志 ============= ：接受到商品库存的读取请求，商品 id = {}", productId);

        // 1. 构建获取请求体 不需要强制刷新缓存，。根据情况来抉择是否刷新
        Request request = new ProductInventoryCacheRefreshRequest(productId, productInventoryService, false);
        // 2. 异步去处理这次的请求是否在上一个更新操作删除缓存的时间吗，=
        requestAsyncProcessService.process(request);

        // 3. 这里需要hang一会，来查缓存，
        long startTime = System.currentTimeMillis();
        long waitTime = 0L;

        while (true) {
            if (waitTime > 20000) {
                // 超过200ms则直接不再查缓存，转入数据库查询
                break;
            }
            // 从redis中给读取商品缓存数据
            ProductInventory productInventory = productInventoryService.getProductInventoryCache(productId);
            // 如果缓存中存在数据则直接返回数据
            if (null != productInventory) {
                log.info("============= 日志 ============= ：在200ms内读取到缓存中的库存信息，商品 id = {}", productId);
                return Response.ok(productInventory);
            }
            // 如果没有，则需要等待20ms
            try {
                TimeUnit.MILLISECONDS.sleep(20);
                waitTime = System.currentTimeMillis() - startTime;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 超过200ms还没拿到数据，则直接查数据库
        log.info("============= 日志 ============= ：200ms 没查询到数据， 商品id={} ", productId);
        final ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        if (productInventory != null) {
            //查完库进行一次读请求的处理
            // 这里也是一次读请求的操作，所以也要放入到队列中去处理，不然可能会导致数据不一致
            // 这里的执行必须要强制刷新缓存一次，下面1 的问题
            // 设置 true 强制刷新缓存
            requestAsyncProcessService.process(new ProductInventoryCacheRefreshRequest(productId, productInventoryService, true));
            /**
             * 程序执行到这里，有以下情况：
             * 1. 之前执行了读请求，将 flag 设置为了false ，之后 redis 因为 lru 而清除了，这时差不到缓存，需要再刷新一次
             *      这种情况必须强制刷新缓存，不然读线程处理时不会处理这次的请求，因为 flag 为 false
             * 2. 在 200ms 内读请求一直挤压在队列中，没有得到执行，这种概率很小，但是出现这种说明程序处理能力有问题了
             * 3. 数据库本身就没，缓存穿透，请求直接到达数据库
             *
             */
        }

        return Response.ok(productInventory);
    }

}
