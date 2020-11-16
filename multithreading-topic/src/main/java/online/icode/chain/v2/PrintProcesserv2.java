package online.icode.chain.v2;

import online.icode.chain.Request;
import online.icode.chain.v1.IDoProcesser;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/10/3 9:52
 */
public class PrintProcesserv2 extends Thread implements IDoProcesser {

    private static BlockingQueue<Request> requestBlockingQueue = new LinkedBlockingQueue<>();
    private IDoProcesser nextProcesser;

    public PrintProcesserv2(IDoProcesser nextProcesser) {
        this.nextProcesser = nextProcesser;
    }


    @Override
    public void run() {

        while (true) {
            //业务逻辑
            Request request = null;
            try {
                request = requestBlockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (null != request){

                System.out.println("PrintProcesser ---> 处理请求：" + request+" SaveProcesser队列已存 ======>"+ requestBlockingQueue.size());
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (null != nextProcesser) {
                    nextProcesser.process(request);
                }
            }
        }
    }

    @Override
    public void process(Request request) {
        System.out.println("PrintProcesser 添加请求： "+request);
        requestBlockingQueue.add(request);
    }
}
