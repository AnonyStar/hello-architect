package online.icode.chain.v1;

import online.icode.chain.Request;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/10/3 9:52
 */
public class PrintProcesser implements IDoProcesser {

    private IDoProcesser nextProcesser;

    public PrintProcesser(IDoProcesser nextProcesser) {
        this.nextProcesser = nextProcesser;
    }

    @Override
    public void process(Request request) {
        //业务逻辑
        System.out.println("PrintProcesser ---> 处理请求："+ request);
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (null != nextProcesser){
            nextProcesser.process(request);
        }
    }
}
