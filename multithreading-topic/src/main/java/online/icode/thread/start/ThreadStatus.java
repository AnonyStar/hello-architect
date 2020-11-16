package online.icode.thread.start;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/9/24 15:53
 */
public class ThreadStatus {

    public static void main(String[] args) {
        ////TIME_WAITING 通过 sleep wait（time） 来进入等待超时中
        new Thread(() -> {
           while (true){
               //线程执行内容
               try {
                   TimeUnit.SECONDS.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        },"Time_Waiting").start();
        //WAITING， 线程在 ThreadStatus 类锁上通过 wait 进行等待
        new Thread(() -> {
            while (true){
                synchronized (ThreadStatus.class){
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Thread_Waiting").start();

        //synchronized 获得锁，则另一个进入阻塞状态 blocked
        new Thread(() -> {
            while (true){
                synchronized(Object.class){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Object_blocked_1").start();
        new Thread(() -> {
            while (true){
                synchronized(Object.class){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Object_blocked_2").start();
    }
}
