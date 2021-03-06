package online.icode.eshop.inventory.listener;

import online.icode.eshop.inventory.thread.RequestProcessorThreadPool;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: zhoucx
 * @time: 2021/3/6 20:45
 */
@Component
public class InitListener  implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //容器初始化后 初始化请求线程池
        RequestProcessorThreadPool.init();
    }
}
