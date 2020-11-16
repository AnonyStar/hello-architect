package online.icode.thread.stop;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ulr: i-code.online
 * @author: zhoucx
 * @time: 2020/9/25 14:45
 */
public class MarkThreadTest {

    //定义标记为 使用 volatile 修饰
    private static volatile  boolean mark = false;

    @Test
    public void markTest(){
        new Thread(() -> {
            //判断标记位来确定是否继续进行
            while (!mark){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程执行内容中...");
            }
        }).start();

        System.out.println("这是主线程走起...");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //10秒后将标记为设置 true 对线程可见。用volatile 修饰
        mark = true;
        System.out.println("标记位修改为："+mark);
    }
}
