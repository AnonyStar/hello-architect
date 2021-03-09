package online.icode.eshop.inventory.core.service.impl;

import online.icode.eshop.inventory.core.request.Request;
import online.icode.eshop.inventory.core.request.RequestQueue;
import online.icode.eshop.inventory.core.service.RequestAsyncProcessService;
import org.springframework.stereotype.Service;

/**
 * @author: zhoucx
 * @time: 2021/3/7 10:59
 */
@Service
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {

    @Override
    public void process(Request request) {
        RequestQueue.getInstance().addRequest(request);
    }
}
