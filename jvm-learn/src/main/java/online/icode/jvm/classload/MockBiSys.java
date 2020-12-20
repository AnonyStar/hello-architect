package online.icode.jvm.classload;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/12/17 16:42
 */
public class MockBiSys {


    /*
    JVM参数：-XX:NewSize=100M -XX:MaxNewSize=100M -XX:InitialHeapSize=200M -XX:MaxHeapSize=200M -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3M -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:log.gc
     */
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(30);
        while (true){
            loadData();
        }
    }

    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            data = new byte[1024*1024];
        }
        data = null;
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
