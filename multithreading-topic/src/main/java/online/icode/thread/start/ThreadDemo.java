package online.icode.thread.start;

import java.util.concurrent.TimeUnit;

/**
 * url: www.i-code.online
 * @author: anonyStar
 * @time: 2020/9/24 18:55
 */
public class ThreadDemo extends Thread {
    @Override
    public void run() {
        //线程执行内容
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThredDemo 线程正在执行,线程名："+ Thread.currentThread().getName());
        }
    }
}
