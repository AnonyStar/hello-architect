package online.icode.jvm.classload;

/**
 * @author: zhoucx
 * @time: 2020/12/16 11:05
 */
public class JvmArgument4 {

    /*
    模拟 young GC 后存活对象放不下Survivor区而直接进入老年代中
     */
    private static int _2M = 2*1024*1024;

    public static void main(String[] args) {
        byte[] arr1 = new byte[_2M];
        arr1 = new byte[_2M];
        arr1 = new byte[_2M];

        byte[] arr2 = new byte[128*1024];

        arr2 = null;

        byte[] arr3 = new byte[_2M];

    }
}
