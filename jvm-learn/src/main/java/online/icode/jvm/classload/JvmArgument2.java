package online.icode.jvm.classload;

/**
 *
 * @author: zhoucx
 * @time: 2020/12/15 13:50
 */
public class JvmArgument2 {


    /*
        GC 日志分析
      JVM参数：-XX:NewSize=5M -XX:MaxNewSize=5M -XX:InitialHeapSize=10M -XX:MaxHeapSize=10M -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=10M -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log

     */

    public static void main(String[] args) {
        byte[] arry1 = new byte[1024*1024];
        arry1 = new byte[1024*1024];
        arry1 = new byte[1024*1024];
        arry1 = null;

        byte[] arry2 = new byte[1024*1024*2];
    }

    /* gc 日志
    Java HotSpot(TM) 64-Bit Server VM (25.261-b12) for windows-amd64 JRE (1.8.0_261-b12), built on Jun 18 2020 06:56:32 by "" with unknown MS VC++:1916
    Memory: 4k page, physical 33406776k(15087196k free), swap 38387512k(14820284k free)
    CommandLine flags: -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:MaxNewSize=5242880 -XX:NewSize=5242880 -XX:OldPLABSize=16 -XX:PretenureSizeThreshold=10485760 -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC
    0.103: [GC (Allocation Failure) 0.103: [ParNew: 3777K->512K(4608K), 0.0016994 secs] 3777K->1653K(9728K), 0.0018732 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
     par new generation   total 4608K, used 3746K [0x00000000ff600000, 0x00000000ffb00000, 0x00000000ffb00000)
      eden space 4096K,  78% used [0x00000000ff600000, 0x00000000ff9289b8, 0x00000000ffa00000)
      from space 512K, 100% used [0x00000000ffa80000, 0x00000000ffb00000, 0x00000000ffb00000)
      to   space 512K,   0% used [0x00000000ffa00000, 0x00000000ffa00000, 0x00000000ffa80000)
     concurrent mark-sweep generation total 5120K, used 1141K [0x00000000ffb00000, 0x0000000100000000, 0x0000000100000000)
     Metaspace       used 3209K, capacity 4496K, committed 4864K, reserved 1056768K
      class space    used 352K, capacity 388K, committed 512K, reserved 1048576K
     */
}
