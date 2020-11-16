package online.icode.thread.stop;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/9/27 11:12
 */
public class InterruptTest {

    @Test
    public void interrupt(){
        //创建 interrupt-1 线程

        Thread thread = new Thread(() -> {
            while (true) {
                //判断当前线程是否中断，
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程1 接收到中断信息，中断线程...");
                }
                System.out.println(Thread.currentThread().getName() + "线程正在执行...");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "interrupt-1");
        //启动线程 1
        thread.start();

        //创建 interrupt-2 线程
        new Thread(() -> {
            int i = 0;
            while (i <10){
                System.out.println(Thread.currentThread().getName()+"线程正在执行...");
                if (i == 8){
                    //通知线程1 设置中断通知
                    thread.interrupt();
                }
                i ++;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"interrupt-2").start();

    }

    public static void main(String[] args) {
        //创建 interrupt-1 线程

        Thread thread = new Thread(() -> {
            while (true) {
                //判断当前线程是否中断，
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程1 接收到中断信息，中断线程...中断标记：" + Thread.currentThread().isInterrupted());
                    Thread.interrupted(); // //对线程进行复位，由 true 变成 false
                    System.out.println("经过 Thread.interrupted() 复位后，中断标记：" + Thread.currentThread().isInterrupted());

                    //再次判断是否中断，如果是则退出线程
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "线程正在执行...");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "interrupt-1");
        //启动线程 1
        thread.start();

        //创建 interrupt-2 线程
        new Thread(() -> {
            int i = 0;
            while (i <20){
                System.out.println(Thread.currentThread().getName()+"线程正在执行...");
                if (i == 8){
                    System.out.println("设置线程中断...." );
                    //通知线程1 设置中断通知
                    thread.interrupt();

                }
                i ++;
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"interrupt-2").start();
    }

}
