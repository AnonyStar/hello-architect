package online.icode.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhoucx
 * @version 1.0.0
 * @ClassName MyThreadPool.java
 * @Description TODO
 * @createTime 2022年11月10日 23:03:00
 */
public class MyThreadPool {

    private BlockingQueue<Runnable> queue;

    private List<Thread> threads = new ArrayList();

    public MyThreadPool(int coreNum, BlockingQueue<Runnable> queue) {
        this.queue = queue;
        for (int i = 0; i < coreNum; i++) {
            Thread thread = new WorkThread();
            thread.start();
            threads.add(thread);
        }
    }

    public void execute(Runnable runnable) {
        try {
            queue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    class WorkThread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    Runnable take = queue.take();
                    take.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
