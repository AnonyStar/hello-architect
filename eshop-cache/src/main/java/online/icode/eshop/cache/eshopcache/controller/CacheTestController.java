package online.icode.eshop.cache.eshopcache.controller;

import online.icode.eshop.cache.eshopcache.model.ProductInfo;
import online.icode.eshop.cache.eshopcache.service.CacheTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhoucx
 * @time: 2021/3/13 11:15
 */
@RestController
public class CacheTestController {

    @Resource
    private CacheTestService cacheTestService;

    @GetMapping("getcache")
    public ProductInfo getCache(Long id) {
        return cacheTestService.getLocalCache(id);
    }


    @PostMapping("putcache")
    public void putCache(@RequestBody ProductInfo info) {
        cacheTestService.saveLocalCache(info);
    }

}
