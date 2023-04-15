package online.icode.juc;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zhoucx
 * @version 1.0.0
 * @ClassName CopyOnWriteArrayListTest.java
 * @Description TODO
 * @createTime 2022年11月08日 21:41:00
 */
public class CopyOnWriteArrayListTest {


    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("test");
        list.set(0, "admin");

    }
}
