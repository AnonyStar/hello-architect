package online.icode.eshop.inventory.core.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

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

    /**
     * 标记位map ，记录是否需要处理当前队列中的请求 ，用于读操作请求去重
     */
    private Map<String, Boolean> flagMap = new ConcurrentHashMap<>();

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


    public  void addRequest(Request request) {
        //获取当前求情应该存放的队列
        final BlockingQueue<Request> routingQueue = getRoutingQueue(request.getProductId());
        // 将请求放入队列中
        routingQueue.offer(request);
    }

    private BlockingQueue<Request> getRoutingQueue(Integer productId) {
        final RequestQueue instance = getInstance();
        String key = String.valueOf(productId);
        //这里通过hash值计算出索引位置，利用类似hashmap的hash计算方式，将高十六位充分利用到计算中
        int h;
        int hash = (key == null) ? 0 :(h = key.hashCode()) ^ (h >>> 16);
        int index = hash & (requestQueue.size() - 1) ;
        return requestQueue.get(index);
    }

    public Boolean getFlagMap(String key) {
        return flagMap.get(key);
    }

    public void putFlagMap(String key, Boolean flag) {
        this.flagMap.put(key, flag);
    }
}
