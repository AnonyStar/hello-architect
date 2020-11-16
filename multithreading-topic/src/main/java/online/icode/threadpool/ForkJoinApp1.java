package online.icode.threadpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @url: i-code.online
 * @author: AnonyStar
 * @time: 2020/11/2 10:01
 */
public class ForkJoinApp1 {


    public static void main(String[] args) {
        // 创建线程池，
        ForkJoinPool joinPool = new ForkJoinPool();
        // 创建根任务
        SubTask subTask = new SubTask(0,200);
        // 提交任务
        joinPool.submit(subTask);
        //让线程阻塞等待所有任务完成 在进行关闭
        try {
            joinPool.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        joinPool.shutdown();
    }
}


class  SubTask extends RecursiveAction {

    int startNum;
    int endNum;

    public SubTask(int startNum,int endNum){
        super();
        this.startNum = startNum;
        this.endNum = endNum;
    }


    @Override
    protected void compute() {

        if (endNum - startNum < 10){
            // 如果分裂的两者差值小于10 则不再继续，直接打印
            System.out.println(Thread.currentThread().getName()+": [startNum:"+startNum+",endNum:"+endNum+"]");
        }else {
            // 取中间值
            int middle = (startNum + endNum) / 2;
            //创建两个子任务，以递归思想，
            SubTask subTask = new SubTask(startNum,middle);
            SubTask subTask1 = new SubTask(middle,endNum);
            //执行任务， fork() 表示异步的开始执行
            subTask.fork();
            subTask1.fork();
        }
    }
}