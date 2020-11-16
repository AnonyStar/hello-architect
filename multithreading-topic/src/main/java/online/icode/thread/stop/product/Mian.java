package online.icode.thread.stop.product;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @url: i-code.online
 * @author: zhoucx
 * @time: 2020/10/12 11:08
 */
public class Mian {


    public static void main(String[] args) {
        BlockingQueue queue = new LinkedBlockingQueue(10);

        Producter producter = new Producter(queue);
        Consumer consumer = new Consumer(queue);

        Thread thread = new Thread(producter,"producter-Thread");
        thread.start();
        new Thread(consumer,"COnsumer-Thread").start();

    }
}
