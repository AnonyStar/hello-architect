package online.icode.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @url: i-code.online
 * @author: AnonyStar
 * @time: 2020/11/2 10:01
 */
public class ForkJoinApp3 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        //计算第二是项的数值
        final ForkJoinTask<Integer> submit = pool.submit(new Fibonacci(20));
        // 获取结果，这里获取的就是异步任务的最终结果
        System.out.println(submit.get());

    }
}


class Fibonacci extends RecursiveTask<Integer>{

    int num;
    public Fibonacci(int num){
        this.num = num;
    }

    @Override
    protected Integer compute() {
        if (num <= 1) return num;
        //创建子任务
        Fibonacci subTask1 = new Fibonacci(num - 1);
        Fibonacci subTask2 = new Fibonacci(num - 2);
        // 执行子任务
        subTask1.fork();
        subTask2.fork();
        //获取前两项的结果来计算和
        return subTask1.join()+subTask2.join();
    }
}
