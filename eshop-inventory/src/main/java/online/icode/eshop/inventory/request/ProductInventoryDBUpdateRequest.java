package online.icode.eshop.inventory.request;

import online.icode.eshop.inventory.model.ProductInventory;
import online.icode.eshop.inventory.service.ProductInventoryService;

/**
 * 当更新数据时：
 * cache aside pattern
 *  1. 删除缓存
 *  2. 更新数据库
 *
 * @author: zhoucx
 * @time: 2021/3/6 23:52
 */
public class ProductInventoryDBUpdateRequest implements Request {

    private ProductInventory productInventory;

    private ProductInventoryService productInventoryService;

    public ProductInventoryDBUpdateRequest(ProductInventory productInventory,
                                           ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }


    @Override
    public void process() {
        // 1. 删除缓存
        productInventoryService.removeProductInventoryCache(productInventory);
        // 2. 更新数据库
        productInventoryService.updateProductInventory(productInventory);
    }
}
