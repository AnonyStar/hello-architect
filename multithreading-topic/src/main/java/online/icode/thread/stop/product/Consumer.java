package online.icode.thread.stop.product;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @url: i-code.online
 * @author: zhoucx
 * @time: 2020/10/12 11:03
 */
public class Consumer implements Runnable{

    BlockingQueue numQueue;

    public Consumer(BlockingQueue numQueue){
        this.numQueue = numQueue;
    }

    @Override
    public void run() {

        try {
            while (Math.random() < 0.97){
                //进行消费
                System.out.println(numQueue.take()+"被消费了...");;
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("消费者执行结束...");
            Producter.mark = false;
            System.out.println("Producter.mark = "+Producter.mark);
        }

    }
}
