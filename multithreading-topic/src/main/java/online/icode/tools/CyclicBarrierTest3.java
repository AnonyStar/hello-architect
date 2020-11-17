package online.icode.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: zhoucx
 * @time: 2020/11/17 11:11
 */
public class CyclicBarrierTest3 {

    /*
    测试 CyclicBarrier(int num,Runnable action) 中各个线程执行的顺序
      */

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,() ->{
            System.out.println("执行 barrierAction ---");
        });

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() +"等待...");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +"执行...");
        },"线程A ").start();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() +"等待...");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +"执行...");
        },"线程B ").start();
        System.out.println("主线程等待");
        cyclicBarrier.await();
        System.out.println("这是主线程...");
    }
}
