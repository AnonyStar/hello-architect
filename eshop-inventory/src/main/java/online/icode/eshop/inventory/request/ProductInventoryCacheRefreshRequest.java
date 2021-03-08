package online.icode.eshop.inventory.request;

import lombok.extern.slf4j.Slf4j;
import online.icode.eshop.inventory.model.ProductInventory;
import online.icode.eshop.inventory.request.base.CacheRefreshRequest;
import online.icode.eshop.inventory.service.ProductInventoryService;

/**
 *
 * @author: zhoucx
 * @time: 2021/3/6 23:51
 */
@Slf4j
public class ProductInventoryCacheRefreshRequest extends CacheRefreshRequest {

    private Integer productId;

    /**
     * 指定是否需要强制刷新缓存，默认为fasle
     * 当出现 redis 中缓存失效时，。读请求读不到数据，发起一次查库，之后再发起一次缓存刷新操作
     */
    private Boolean forceRefresh = false;

    private ProductInventoryService productInventoryService;

    public ProductInventoryCacheRefreshRequest(Integer productId,
                                               ProductInventoryService productInventoryService,
                                               Boolean forceRefresh) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
        this.forceRefresh = forceRefresh;
    }

    @Override
    public void process() {
        // 1. 读取数据库
        final ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        log.info("============= 日志 ============= ：已查询到商品最新的库存数量，商品  = {}", productInventory);
        // 2. 将最新的信息更新到redis缓存中
        productInventoryService.setProductInventoryCache(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productId;
    }

    @Override
    public Boolean isForceRefresh() {
        return forceRefresh;
    }
}
