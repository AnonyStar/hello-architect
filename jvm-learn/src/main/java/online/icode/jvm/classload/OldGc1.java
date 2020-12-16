package online.icode.jvm.classload;

/**
 * @author: zhoucx
 * @time: 2020/12/16 14:34
 */
public class OldGc1 {

    /*
    JVM参数：
    -XX:NewSize=10M -XX:MaxNewSize=10M -XX:InitialHeapSize=20M -XX:MaxHeapSize=20M -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3M -XX:MaxTenuringThreshold=15 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log

    模拟老年代垃圾回收
     */

    private static int _2M = 2*1024*1024;
    private static int _3M = 3*1024*1024;
    private static int _4M = 4*1024*1024;
    private static int _128K = 128*1024;


    public static void main(String[] args) {
        //新生代10M
        //老年代 10M

        byte[] arr1 = new byte[_4M];    //直接放入老年代，取消引用，可回收
        arr1 = null;

        byte[] arr2 = new byte[_2M];
        byte[] arr3 = new byte[_2M];
        byte[] arr4 = new byte[_2M];
        byte[] arr5 = new byte[_128K];
        //上面四个放入Eden区

        byte[] arr6 = new byte[_2M];  //新生代放不下 触发young GC

        //触发完后存活对象全部 无法放入survivor区，则放入老年代 老年代放不下触发 old GC
    }
}
