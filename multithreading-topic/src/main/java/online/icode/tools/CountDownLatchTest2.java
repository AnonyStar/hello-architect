package online.icode.tools;

import java.util.concurrent.CountDownLatch;

/**
 * @author: zhoucx
 * @time: 2020/11/16 18:15
 */
public class CountDownLatchTest2 {


    public static void main(String[] args) {
        //模拟跑步比赛，裁判说开始，所有选手开始跑，我们可以使用countDownlatch来实现

        //这里需要等待裁判说开始，所以时等着一个线程
        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(() ->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"开始跑~~");

        },"选手1").start();
        new Thread(() ->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"开始跑~~");

        },"选手2").start();

        System.out.println("预备~~~");
        countDownLatch.countDown();
        System.out.println("跑~~~");
    }
}
