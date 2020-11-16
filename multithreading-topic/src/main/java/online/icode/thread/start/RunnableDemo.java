package online.icode.thread.start;

import java.util.concurrent.TimeUnit;

/**
 * url: www.i-code.online
 * @author: anonyStar
 * @time: 2020/9/24 18:55
 */
public class RunnableDemo implements Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        //线程执行内容
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("RunnableDemo 线程正在执行,线程名："+ Thread.currentThread().getName());
        }
    }
}
