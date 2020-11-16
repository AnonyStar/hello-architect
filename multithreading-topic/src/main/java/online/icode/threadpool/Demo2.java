package online.icode.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @url: i-code.online
 * @author: AnonyStar
 * @time: 2020/10/23 10:36
 */
public class Demo2 {


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(
                        1,
                        2,
                        1,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(),
                        new ThreadPoolExecutor.DiscardOldestPolicy()
                );
    }



}
