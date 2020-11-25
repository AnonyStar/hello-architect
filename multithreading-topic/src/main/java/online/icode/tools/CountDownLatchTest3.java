package online.icode.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/11/25 14:26
 */
public class CountDownLatchTest3 {


    public static void main(String[] args) throws InterruptedException {
        /**
         * i-code.online
         * 云栖简码
         */
        //等待的个数
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "从住所出发...");
                try {
                    TimeUnit.SECONDS.sleep((long) (Math.random()*10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 到达目的地-----");
                countDownLatch.countDown();
            },"人员-"+i).start();
        }

        System.out.println("大巴正在等待人员中.....");
        countDownLatch.await();
        System.out.println("-----所有人到齐，出发-----");
    }
}
