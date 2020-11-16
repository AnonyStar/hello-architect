package online.icode.chain.v1;

import online.icode.chain.Request;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/10/3 9:31
 */
public class RequestProcesser implements IDoProcesser {


    private IDoProcesser nextProcesser;

    public RequestProcesser(IDoProcesser nextProcesser) {
        this.nextProcesser = nextProcesser;
    }

    @Override
    public void process(Request request) {
        System.out.println("RequestProcesser ---> 处理请求："+request);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nextProcesser.process(request);
    }
}
