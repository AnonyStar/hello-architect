package online.icode.thread.stop.product;

import java.util.concurrent.BlockingQueue;

/**
 * @url: i-code.online
 * @author: zhoucx
 * @time: 2020/10/12 10:46
 */
public class Producter implements Runnable {

    //标记是否需要产生数字
    public static volatile boolean mark = true;

    BlockingQueue<Integer> numQueue;

    public Producter(BlockingQueue numQueue){
        this.numQueue = numQueue;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < 100000 && mark){
                //生产数字，加入到队列中
                if (num % 50 == 0 ){
                    System.out.println(num + " 是50的倍数，加入队列");
                    numQueue.put(num);
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者运行结束....");
        }
    }
}
