package online.icode.eshop.inventory.service.impl;

import online.icode.eshop.inventory.model.ProductInventory;
import online.icode.eshop.inventory.service.ProductInventoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: zhoucx
 * @time: 2021/3/6 23:40
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductInventoryServiceImplTest {
    @Resource
    private ProductInventoryService productInventoryService;

    @Test
    public void updateProductInventory() {
        ProductInventory inventory = new ProductInventory();

        productInventoryService.updateProductInventory(inventory);
    }

    @Test
    public void removeProductInventoryCache() {
        ProductInventory inventory = new ProductInventory();
        inventory.setProductId(10001);
        productInventoryService.removeProductInventoryCache(inventory);
    }

    @Test
    public void setProductInventoryCache() {
        ProductInventory inventory = new ProductInventory();
        inventory.setProductId(10002);
        inventory.setInventoryCnt(100L);
        productInventoryService.setProductInventoryCache(inventory);
    }

}