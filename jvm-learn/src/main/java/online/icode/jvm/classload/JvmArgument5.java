package online.icode.jvm.classload;

/**
 * @author: zhoucx
 * @time: 2020/12/16 12:57
 */
public class JvmArgument5 {

    /*
    JVm参数：-XX:NewSize=10M -XX:MaxNewSize=10M -XX:InitialHeapSize=20M -XX:MaxHeapSize=20M -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=2M -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
    模拟大对象进入老年代
     */
    private static int _3M = 3*1024*1024;
    private static int _2M = 2*1024*1024;

    public static void main(String[] args) {
        //新生代10M 设置大对象2M
        byte[] arr = new byte[_3M];
        arr = new byte[_2M];
        arr = new byte[_2M];
       // arr = new byte[_3M];

        arr = null;
    }
}
