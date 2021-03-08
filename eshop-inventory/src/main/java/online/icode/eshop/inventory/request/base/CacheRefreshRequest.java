package online.icode.eshop.inventory.request.base;

import online.icode.eshop.inventory.request.base.Request;

/**
 * @author: zhoucx
 * @time: 2021/3/8 10:40
 */
public abstract class CacheRefreshRequest implements Request {
    public abstract Boolean isForceRefresh();
}
