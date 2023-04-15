package online.icode;

import online.icode.threadpool.MyThreadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoucx
 * @version 1.0.0
 * @ClassName ${NAME}.java
 * @Description TODO
 * @createTime ${YEAR}年${MONTH}月${DAY}日 ${HOUR}:${MINUTE}:00
 */
public class Main {
    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(6, new LinkedBlockingQueue<>());
        for (int i = 0; i < 20; i++) {
            pool.execute(() -> {
                System.out.println("执行线程内容....");
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }
}