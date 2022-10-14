package online.icode.jvm.aotmic;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {


    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        Unsafe unsafe = Unsafe.getUnsafe();
//        unsafe.compareAndSwapInt()
    }
}
