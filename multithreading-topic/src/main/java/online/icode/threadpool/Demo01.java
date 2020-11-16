package online.icode.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zhoucx
 * @time: 2020/10/22 9:47
 */
public class Demo01 {

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
    }
}
