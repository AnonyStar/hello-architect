package online.icode.jvm.classload;

/**
 * @author: zhoucx
 * @time: 2020/12/16 15:25
 */
public class OldGc2 {

    /*
    老年代可用空间小于每次回收的平均值
     */
    private static int _2M = 2*1024*1024;
    private static int _3M = 3*1024*1024;
    private static int _4M = 4*1024*1024;
    private static int _128K = 128*1024;
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            byte[] arr = new byte[_3M];
            byte[] arr1 = new byte[_128K];
        }
    }

    class Obj{
        private byte[] arr;
        public Obj(int size){
            this.arr = new byte[size];
        }
    }
}
