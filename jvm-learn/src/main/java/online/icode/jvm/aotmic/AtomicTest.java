package online.icode.jvm.aotmic;

import java.util.concurrent.locks.ReentrantLock;

public class AtomicTest {


    private static volatile int data = 0;
    public static void main(String[] args) {
//        m1();

    }


    private static void m1() {
        ReentrantLock reentrantLock = new ReentrantLock();
        try {
            reentrantLock.lock();
            //业务代码。需要加锁的 代码.
            data++;
        } finally {
            reentrantLock.unlock();
        }
    }
}
