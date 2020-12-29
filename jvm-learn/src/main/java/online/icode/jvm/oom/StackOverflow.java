package online.icode.jvm.oom;

/**
 * @author: zhoucx
 * @time: 2020/12/22 10:07
 */
public class StackOverflow {

    private static int count = 0;
    /*
    -Xss1M  设置虚拟机栈的大小
     */
    public static void main(String[] args) {
        work();
    }

    public static void work(){
        count++;
        System.out.println("第" + count + "调用work方法...");

        work();
    }
}
