package online.icode.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/11/17 18:05
 */
public class SemaphoreTest2 {

    private static int count = 0;
    /*
        Semaphore 中如果我们允许的的许可证数量为1 ，那么它的效果与锁相似。
     */
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService service = Executors.newFixedThreadPool(10);

        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 10000; i++) {
            service.submit(() ->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行了");
                count ++;
                semaphore.release();
//                try {
//                    TimeUnit.MILLISECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            });
        }
        service.shutdown();
        TimeUnit.SECONDS.sleep(5);
        System.out.println(count);

    }
}
