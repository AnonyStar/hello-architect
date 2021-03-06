package online.icode.eshop.inventory.thread;

import online.icode.eshop.inventory.request.Request;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * 执行请求的工作线程.
 * @author: zhoucx
 * @time: 2021/3/6 22:21
 */
public class RequestProcessorThread implements Callable<Boolean> {


    /**
     * 自己监控的内存队列.
     */
    private BlockingQueue<Request> queue;

    /**
     * 创建线程时初始化线程内的队列
     * @param requests
     */
    public RequestProcessorThread(BlockingQueue<Request> requests) {
        this.queue = requests;
    }

    @Override
    public Boolean call() throws Exception {
        while (true) {
            //todo 待写业务
            break;
        }
        return true;
    }
}
