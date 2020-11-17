package online.icode.tools;

import java.util.concurrent.CountDownLatch;

/**
 * @author: zhoucx
 * @time: 2020/11/16 16:36
 */
public class CountDownLatchTest {
    /**
     * 等待一个或者多个线程直线完成再执行，调用了await方法的线程，
     */

    //指定需要需要被等待的线程数，当两个线程都执行完，则执行等待的线程
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() ->{
            System.out.println("这是线程1");
            countDownLatch.countDown();
        },"t1").start();

        new Thread(() ->{
            System.out.println("线程2- ");
            countDownLatch.countDown();
        },"t2").start();

        countDownLatch.await();

        System.out.println("main 主线程~ 3");

    }
}
