package online.icode.thread.sync;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: zhoucx
 * @time: 2020/9/28 15:57
 */
public class PerformaceLock {


    private static long count1 = 0;

    private static AtomicLong count2 = new AtomicLong(0);

    private static LongAdder count3 = new LongAdder();


    /*synchronized*/ void sync(){
        synchronized (Object.class){
            for (int i = 0; i < 100000; i++) {
                count1++;
            }
        }

    }

    void atomic(){
        for (int i = 0; i < 100000; i++) {
            count2.incrementAndGet();
        }
    }
    void longAdder(){
        for (int i = 0; i < 1000000; i++) {
            count3.increment();
        }
    }

    /**
     * 测试 synchronzied  atomic  的性能差异   采用 ++ 运算 ，加载一百万次
     * @param args
     */
    public static void main(String[] args) {

        List<Thread> threads = new LinkedList<>();
        PerformaceLock lock = new PerformaceLock();

        //创建10000线程
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() ->{
                for (int j = 0; j < 1000000; j++) {
                    synchronized (lock){
                        PerformaceLock.count1++;
                    }
                }
            },"Thread-"+i));
        }
        List<Thread> threads1 = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            threads1.add(new Thread(() ->{
                for (int j = 0; j < 100000; j++) {
                    count2.incrementAndGet();
                }
            },"atomic-"+i));
        }
        List<Thread> threads2 = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            threads2.add(new Thread(lock::longAdder,"longadder-"+i));
        }

        long start1 = System.currentTimeMillis();
        threads.forEach(s -> s.start());
        threads.forEach(s ->{
            try {
                s.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("synchronzied : "+ (System.currentTimeMillis() - start1) + " count:"+ PerformaceLock.count1);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long start2 = System.currentTimeMillis();
        threads1.forEach(Thread::start);
        threads1.forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Atomic : "+ (System.currentTimeMillis()-start2)+" count2:"+ lock.count2);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long start3 = System.currentTimeMillis();
        threads2.forEach(Thread::start);
        threads2.forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("LongAdder : "+ (System.currentTimeMillis()-start3) +" count3:"+ lock.count3);
    }

    @Test
    public void atomicTest(){

        AtomicLong count = new AtomicLong(0);
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                for (int j = 0; j < 1000000; j++) {
                    count.incrementAndGet();
                }
            },"Thread_"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count: "+count);


    }



}
