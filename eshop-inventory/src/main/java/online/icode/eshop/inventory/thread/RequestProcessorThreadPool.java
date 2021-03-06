package online.icode.eshop.inventory.thread;

import online.icode.eshop.inventory.request.Request;
import online.icode.eshop.inventory.request.RequestQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 请求处理线程池 采用单例
 * @author: zhoucx
 * @time: 2021/3/6 22:29
 */
public class RequestProcessorThreadPool {


    /**
     * 创建一个容量为10的线程池，
     * 实际使用这里线程池的大小可以配置到配置文件中
     */
    private ExecutorService threadPool = new ThreadPoolExecutor(
            10,
            10,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());


    private RequestProcessorThreadPool() {
        RequestQueue request = RequestQueue.getInstance();
        for (int i = 0; i < 10; i++) {
            BlockingQueue<Request> queue = new ArrayBlockingQueue<>(100);
            request.addQueue(queue);
            threadPool.submit(new RequestProcessorThread(queue));
        }
    }


    /**
     * 单例 内部类实现单列，绝对线程安全，采用了jvm的机制
     */
    private static class Singleton {
        private static RequestProcessorThreadPool instance;
        static {
            instance = new RequestProcessorThreadPool();
        }
        public static RequestProcessorThreadPool getInstance() {
            return instance;
        }
    }


    public static RequestProcessorThreadPool getInstance() {
        return Singleton.getInstance();
    }

    public static void init() {
        getInstance();
    }

}
