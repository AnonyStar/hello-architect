package online.icode.eshop.inventory.service.impl;

import online.icode.eshop.inventory.mapper.ProductInventoryMapper;
import online.icode.eshop.inventory.model.ProductInventory;
import online.icode.eshop.inventory.service.ProductInventoryService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhoucx
 * @time: 2021/3/6 23:06
 */
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Resource
    private ProductInventoryMapper productInventoryMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryMapper.updateById(productInventory);
    }

    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        final Boolean delete = redisTemplate.delete(key);
        System.out.println("删除缓存：" + delete);
    }

    @Override
    public ProductInventory findProductInventory(Integer productId) {
        return productInventoryMapper.selectById(productId);
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisTemplate.opsForValue().set(key, productInventory);
    }
}
