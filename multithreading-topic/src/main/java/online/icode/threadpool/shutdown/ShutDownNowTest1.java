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
public class ShutDownNowTest1 {


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
                    System.out.println("接受中断，结束线程~~");
                    return;
                }
                System.out.println("args = " + Arrays.deepToString(args)+ Thread.currentThread().getName());
            });
        }

      final List<Runnable> runnables = service.shutdownNow();
       System.out.println(runnables);
    }
}
