package online.icode.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/10/30 17:15
 */
public class Demo4 {


    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        service.submit(() -> {

        });

    }
}
