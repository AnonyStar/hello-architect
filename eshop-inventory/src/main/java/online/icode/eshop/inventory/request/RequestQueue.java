package online.icode.eshop.inventory.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * 内存队列实现 单例
 * @author: zhoucx
 * @time: 2021/3/6 22:43
 */
public class RequestQueue {

    /**
     * 内存队列
     */
    private List<BlockingQueue<Request>> requestQueue = new ArrayList<>();


    private RequestQueue() {

    }

    /**
     * 内部静态类实现单例
     */
    private static class Singleton {
        private static RequestQueue instance;

        static {
            instance = new RequestQueue();
        }

        public static RequestQueue getInstance() {
            return instance;
        }
    }

    /**
     * 获取当前类的实列
     * @return
     */
    public static RequestQueue getInstance() {
        return Singleton.getInstance();
    }

    public void addQueue(BlockingQueue<Request> queue) {
        this.requestQueue.add(queue);
    }
}
