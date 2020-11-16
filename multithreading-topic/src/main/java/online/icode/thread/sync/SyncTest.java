package online.icode.thread.sync;

import java.util.concurrent.TimeUnit;

/**
 * 测试可重入锁.
 * @author: zhoucx
 * @time: 2020/9/28 15:20
 */
public class SyncTest {


    /**
     * 同步方法1
     */
    synchronized void sync01(){
        System.out.println("this is a sync01 method   >>start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //调用 sync02 方法，两个同步方法，这里调用 sync02 方法时会
        // 检查当前锁的对象是 SyncTest.class 发现一致，则直接进入执行，可重入/偏向锁
        sync02();
        System.out.println("sync01 method  >>end ");
    }

    /**
     * 同步方法2
     */
    synchronized void sync02(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("this is a sync02 method, it‘s running...");
    }


    public static void main(String[] args) {
        SyncTest syncTest = new SyncTest();
        syncTest.sync01();
    }

}
