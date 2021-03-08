package online.icode.eshop.inventory.service.impl;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("============= 日志 ============= ：删除商品 Redis 缓存， 商品id：{}", productInventory.getProductId());
        final Boolean delete = redisTemplate.delete(key);
        System.out.println("删除缓存：" + delete);
    }

    @Override
    public ProductInventory findProductInventory(Integer productId) {
        log.info("============= 日志 ============= ：查商品数据库信息， 商品id：{}", productId);
        return productInventoryMapper.selectById(productId);
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        log.info("============= 日志 ============= ：更新缓存中商品最新的库存数量，商品  = {}", productInventory);
        String key = "product:inventory:" + productInventory.getProductId();
        log.info("============= 日志 ============= ：key=：{}", key);
        redisTemplate.opsForValue().set(key, productInventory.getInventoryCnt());
    }

    @Override
    public ProductInventory getProductInventoryCache(Integer productId) {
        String key = "product:inventory:" + productId;
        // log.info("============= 日志 ============= ：获取商品信息缓存，商品id：{}, ", productId);
        final Object result = redisTemplate.opsForValue().get(key);
        if (null != result && !"".equals(String.valueOf(result))) {
            return new ProductInventory(productId,Long.parseLong(String.valueOf(result)));
        }
        return null;
    }
}
