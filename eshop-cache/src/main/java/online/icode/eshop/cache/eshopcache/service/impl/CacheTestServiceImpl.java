package online.icode.eshop.cache.eshopcache.service.impl;

import online.icode.eshop.cache.eshopcache.model.ProductInfo;
import online.icode.eshop.cache.eshopcache.service.CacheTestService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author: zhoucx
 * @time: 2021/3/13 11:19
 */
@Service
public class CacheTestServiceImpl implements CacheTestService {

    private static final String CACHE_NAME = "local";

    @CachePut(value = CACHE_NAME, key = "'key_'+#productInfo.getId()")
    @Override
    public ProductInfo saveLocalCache(ProductInfo productInfo) {
        //保存
        return productInfo;
    }

    @Cacheable(value = CACHE_NAME, key = "'key_'+#id")
    @Override
    public ProductInfo getLocalCache(Long id) {

        return null;
    }
}
