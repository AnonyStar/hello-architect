package online.icode.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/11/17 17:00
 */
public class SemaphoreTest1 {

    /*
        Semaphore 是信号量， 可以用来控制线程的并发数，可以协调各个线程，以达到合理的使用公共资源

     */

    public static void main(String[] args) {
        //创建10个容量的线程池
        final ExecutorService service = Executors.newFixedThreadPool(100);
        //设置信号量的值5 ，也就是允许五个线程来执行
        Semaphore s = new Semaphore(5);
        for (int i = 0; i < 100; i++) {
            service.submit(() ->{
                try {
                    s.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("数据库耗时操作"+Thread.currentThread().getName());
                    TimeUnit.MILLISECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在执行....");
                s.release();
            });
        }


    }
}
