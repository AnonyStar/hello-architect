package online.icode.eshop.cache.eshopcache.service;

import online.icode.eshop.cache.eshopcache.model.ProductInfo;

/**
 * @author: zhoucx
 * @time: 2021/3/13 11:18
 */
public interface CacheTestService {

    /**
     * 将商品信息保存到本地缓存中
     * @param productInfo
     * @return
     */
    public ProductInfo saveLocalCache(ProductInfo productInfo);

    /**
     * 从本地缓存中获取商品信息
     * @param id
     * @return
     */
    public ProductInfo getLocalCache(Long id);
}
