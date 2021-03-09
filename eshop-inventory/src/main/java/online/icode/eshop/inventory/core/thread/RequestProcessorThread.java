package online.icode.eshop.inventory.core.thread;

import online.icode.eshop.inventory.core.request.CacheRefreshRequest;
import online.icode.eshop.inventory.core.request.DBUpdateRequest;
import online.icode.eshop.inventory.core.request.Request;
import online.icode.eshop.inventory.core.request.RequestQueue;

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
        try {
            while (true) {
                // 获取当前队列的请求，如果队列空则阻塞
                final Request take = queue.take();

                //根据请求来区分具体操作
                if (take instanceof DBUpdateRequest) {
                    // 如果是更新操作，那么必然需要更新刷新缓存，设置为true
                    RequestQueue.getInstance().putFlagMap(String.valueOf(take.getProductId()), true);
                } else if (take instanceof CacheRefreshRequest) {
                    final CacheRefreshRequest cacheRefreshRequest = (CacheRefreshRequest) take;
                    // 设置是否强制刷新缓存，设置强制刷新则直接执行请求
                    if (!cacheRefreshRequest.isForceRefresh()) {
                        final Boolean flag = RequestQueue.getInstance().getFlagMap(String.valueOf(take.getProductId()));
                        // 进行读请求去重
                        /**
                         * 如果为空，那么主要有以下情况：
                         *  1. 数据库本身没没数据，缓存也没数据
                         *  2. 数据库有数据，缓存也有数据，只是没有过写请求和读请求 ，即这是一个初始状态
                         *  3. 有一种特殊情况，即redis因为自身空间满，通过淘汰策略删除了部分数据，那么此时可能会导致数据
                         *     在缓存中没有，但是在数据库中是存在，这时如果没有过读写操作，那么 flag 为空
                         */
                        if (flag == null) {
                            // 默认需要处理这种请求来读库
                            RequestQueue.getInstance().putFlagMap(String.valueOf(take.getProductId()), false);
                        }
                        if (flag!= null && flag) {
                            // 当 flag 不为空时，同时为true 时，那说明队列中已经有 一个 更新请求存在，那么需要刷新缓存
                            RequestQueue.getInstance().putFlagMap(String.valueOf(take.getProductId()), false);
                        }
                        if (flag != null && !flag) {
                            // 如果 flag 为false 那么说明当前丢列中已经存在 一个更新请求 + 一个读请求
                            // 对这种请求直接过略，不需要执行后面的缓存刷新请求了
                            continue;
                        }

                        // 在经过读请求后，flag 的值必然是 false 的，除非有更新请求再次改动，
                        // 但是存在一种问题就是缓存刷新后，此时 flag 已经是false了后面的读请求不会再去刷新缓存了，
                        // 这时redis 因为空间满了，淘汰掉了一份部分数据，那么此时 就会导致读取到的数据一直是空的，
                    }


                }

                // 执行请求操作
                take.process();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
