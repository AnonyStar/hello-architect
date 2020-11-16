package online.icode.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zhoucx
 * @time: 2020/10/28 10:57
 */
public class LockApp01 {

    private static int count =0;

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        List<Thread> list = new ArrayList<>();
        for (int i=0;i<100;i++){
            list.add(new Thread(() ->{
                for (int j =0; j<10000;j++){
                    try {
                        lock.lock();
                        count++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }

            }));
        }

        list.forEach(Thread::start);
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(count);

    }
}
