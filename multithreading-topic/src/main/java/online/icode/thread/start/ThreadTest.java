package online.icode.thread.start;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/9/23 19:50
 */
public class ThreadTest {


    @Test
    public void callable() throws ExecutionException, InterruptedException {
        //创建线程池
        ExecutorService service = Executors.newFixedThreadPool(1);
        //传入Callable实现同时启动线程
        Future submit = service.submit(new CallableDemo());
        //获取线程内容的返回值，便于后续逻辑
        System.out.println(submit.get());
        //关闭线程池
        service.shutdown();
        //主线程
        System.out.println("这是main主线程：" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void thread01(){
        Thread thread = new ThreadDemo();
        thread.setName("线程-1 ");
        thread.start();

        while (true){
            System.out.println("这是main主线程：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void runnableTest(){
        // 本质还是 Thread ，这里直接 new Thread 类，传入 Runnable 实现类
        Thread thread = new Thread(new RunnableDemo(),"runnable子线程 - 1");
        //启动线程
        thread.start();

        while (true){
            System.out.println("这是main主线程：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
