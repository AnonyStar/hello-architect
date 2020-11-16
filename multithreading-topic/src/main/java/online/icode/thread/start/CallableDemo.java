package online.icode.thread.start;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * url: www.i-code.online
 * @author: anonyStar
 * @time: 2020/9/24 18:55
 */
public class CallableDemo implements Callable<String> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        //线程执行内容
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CallableDemo 线程正在执行,线程名："+ Thread.currentThread().getName());

        return "CallableDemo 执行结束。。。。";
    }
}
