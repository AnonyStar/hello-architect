package online.icode.chain.v2;

import online.icode.chain.Request;
import online.icode.chain.v1.IDoProcesser;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/10/3 9:31
 */
public class RequestProcesserv2 extends Thread implements IDoProcesser {

    private static BlockingQueue<Request> requestBlockingQueue = new LinkedBlockingQueue<>();

    private IDoProcesser nextProcesser;

    public RequestProcesserv2(IDoProcesser nextProcesser) {
        this.nextProcesser = nextProcesser;
    }

    @Override
    public void run(){
       while (true){
           //线程处理
           Request request = null;
           try {
               request = requestBlockingQueue.take();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           if ( null != request){
               System.out.println("RequestProcesser ---> 处理请求："+request+" SaveProcesser队列已存 ======>"+ requestBlockingQueue.size());
               try {
                   TimeUnit.MILLISECONDS.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               nextProcesser.process(request);
           }
       }
    }



    @Override
    public void process(Request request) {
        System.out.println("RequestProcesser 添加请求：" + request);
        requestBlockingQueue.add(request);
    }
}
