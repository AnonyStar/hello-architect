package online.icode.eshop.inventory.core.service;

import online.icode.eshop.inventory.core.request.Request;

/**
 * @author: zhoucx
 * @time: 2021/3/7 10:58
 */
public interface RequestAsyncProcessService {


    void process(Request request);
}
