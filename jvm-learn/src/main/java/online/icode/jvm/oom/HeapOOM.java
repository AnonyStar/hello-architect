package online.icode.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhoucx
 * @time: 2020/12/22 10:28
 */
public class HeapOOM {
    /*
    -Xmx10M  -Xmx10M
     */

    public static void main(String[] args) {
        int count  = 0;
        List<Object> list = new ArrayList<>();
        while (true){
            list.add(new Object());
            count++;
           // System.out.println("添加了第"+ count + "个对象了");
        }
    }

}
