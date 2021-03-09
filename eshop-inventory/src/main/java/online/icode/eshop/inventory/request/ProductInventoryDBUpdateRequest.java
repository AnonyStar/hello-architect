package online.icode.eshop.inventory.request;

import lombok.extern.slf4j.Slf4j;
import online.icode.eshop.inventory.model.ProductInventory;
import online.icode.eshop.inventory.core.request.DBUpdateRequest;
import online.icode.eshop.inventory.service.ProductInventoryService;

import java.util.concurrent.TimeUnit;

/**
 * 当更新数据时：
 * cache aside pattern
 *  1. 删除缓存
 *  2. 更新数据库
 *
 * @author: zhoucx
 * @time: 2021/3/6 23:52
 */
@Slf4j
public class ProductInventoryDBUpdateRequest extends DBUpdateRequest {

    private ProductInventory productInventory;

    /**
     * 服务
     */
    private ProductInventoryService productInventoryService;

    public ProductInventoryDBUpdateRequest(ProductInventory productInventory,
                                           ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }


    @Override
    public void process() {
        log.info("============= 日志 ============= ：更新商品库存，商品id:{},商品数量：{}", productInventory.getProductId(), productInventory.getInventoryCnt());
        // 1. 删除缓存
        productInventoryService.removeProductInventoryCache(productInventory);
        log.info("============= 日志 ============= ：成功删除商品 Redis 缓存， 商品id：{}", productInventory.getProductId());
        try {
            // todo 睡眠，演示当更新过程中，缓存删除，还没更新数据库，这是来了读请求
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 2. 更新数据库
        productInventoryService.updateProductInventory(productInventory);
        log.info("============= 日志 ============= ：成功更新商品数据库库存， 商品id：{}", productInventory.getInventoryCnt());
    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }


}
