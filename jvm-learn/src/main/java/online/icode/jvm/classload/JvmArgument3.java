package online.icode.jvm.classload;

/**
 * @author: zhoucx
 * @time: 2020/12/15 16:44
 */
public class JvmArgument3 {

    /*
    JVM参数: -XX:NewSize=10M -XX:MaxNewSize=10M -XX:InitialHeapSize=20M -XX:MaxHeapSize=20M -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=10M -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
      模拟动态年龄判断进入老年代.
     */


    public static void main(String[] args) {
        byte[] arr1 = new byte[1024*1024*2];
         arr1 = new byte[1024*1024*2];
         arr1 = new byte[1024*1024*2];
         arr1 = null;

        byte[] arr2 = new byte[128*1024];
        byte[] arr3 = new byte[2*1024*1024];

        arr3 = new byte[2*1024*1024];
        arr3 = new byte[2*1024*1024];
        arr3 = new byte[128*1024];
        arr3=null;
        byte[] arr4 = new byte[2*1024*1024];


        /*

        Java HotSpot(TM) 64-Bit Server VM (25.261-b12) for windows-amd64 JRE (1.8.0_261-b12), built on Jun 18 2020 06:56:32 by "" with unknown MS VC++:1916
        Memory: 4k page, physical 33406776k(18346152k free), swap 38387512k(18203732k free)
        CommandLine flags: -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:MaxNewSize=10485760 -XX:MaxTenuringThreshold=15 -XX:NewSize=10485760 -XX:OldPLABSize=16 -XX:PretenureSizeThreshold=10485760 -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC
        0.100: [GC (Allocation Failure) 0.100: [ParNew: 8121K->785K(9216K), 0.0005641 secs] 8121K->785K(19456K), 0.0007246 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        0.101: [GC (Allocation Failure) 0.101: [ParNew: 7171K->293K(9216K), 0.0035967 secs] 7171K->1051K(19456K), 0.0036471 secs] [Times: user=0.05 sys=0.02, real=0.00 secs]
        Heap
         par new generation   total 9216K, used 2478K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
          eden space 8192K,  26% used [0x00000000fec00000, 0x00000000fee226c0, 0x00000000ff400000)
          from space 1024K,  28% used [0x00000000ff400000, 0x00000000ff449440, 0x00000000ff500000)
          to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
         concurrent mark-sweep generation total 10240K, used 758K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
         Metaspace       used 3284K, capacity 4496K, committed 4864K, reserved 1056768K
          class space    used 357K, capacity 388K, committed 512K, reserved 1048576K
         */

    }
}
