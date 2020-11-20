package online.icode.threadpool.shutdown;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/11/18 9:42
 */
public class ShutDownTest1 {


    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1000));

        for (int i = 0; i < 1000; i++) {
            service.submit(() ->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("接受中断，不处理~~");
                }
                System.out.println("args = " + Arrays.deepToString(args)+ Thread.currentThread().getName());
            });
        }

        service.shutdown();
        final boolean shutdown = service.isShutdown();
        System.out.println(shutdown);
        final boolean terminated = service.isTerminated();
        System.out.println(terminated);
//        final List<Runnable> runnables = service.shutdownNow();
//        System.out.println(runnables);

        final boolean b = service.awaitTermination(10, TimeUnit.SECONDS);
       // System.out.println(b);
    }
}
