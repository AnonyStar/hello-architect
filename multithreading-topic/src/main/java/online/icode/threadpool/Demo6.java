package online.icode.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: zhoucx
 * @time: 2020/11/5 10:00
 */
public class Demo6 {


    public static void main(String[] args) throws InterruptedException {
       //groupOffer();
        //groupPoll();
       // groupPeek();
        //System.out.println("----------------");
        //groupPeek2();
        groupPut();
        //groupAdd();
        //groupRemove();
        //groupElement();
       // System.out.println("----------------");
        //groupElement2();
    }
    private static void groupAdd() {
        BlockingQueue queue = new ArrayBlockingQueue(2);
        queue.add(1);
        queue.add(2);
        queue.add(3);
    }
    private static void groupRemove() {
        BlockingQueue queue = new ArrayBlockingQueue(2);
        queue.add("i-code.online");
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
    private static void groupElement() {
        BlockingQueue queue = new ArrayBlockingQueue(2);
        queue.add("i-code.online");
        System.out.println(queue.element());
        System.out.println(queue.element());
    }
    private static void groupElement2() {
        BlockingQueue queue = new ArrayBlockingQueue(2);
        System.out.println(queue.element());
    }
    private static void groupOffer() {
        BlockingQueue queue = new ArrayBlockingQueue(2);
        System.out.println(queue.offer("i-code.online"));
        System.out.println(queue.offer("云栖简码"));
        System.out.println(queue.offer("AnonyStar"));
    }
    private static void groupPoll() {
        BlockingQueue queue = new ArrayBlockingQueue(2);
        System.out.println(queue.offer("云栖简码")); //添加元素
        System.out.println(queue.poll()); //取出头元素并且删除
        System.out.println(queue.poll());

    }
    private static void groupPeek() {
        BlockingQueue queue = new ArrayBlockingQueue(2);
        System.out.println(queue.offer(1));
        System.out.println(queue.peek());
        System.out.println(queue.peek());
    }
    private static void groupPeek2() {
        BlockingQueue queue = new ArrayBlockingQueue(2);
        System.out.println(queue.peek());
    }
    private static void addorremove(BlockingQueue queue) {
        //puttake(queue);
        System.out.println(queue.element());

        queue.add(1);
        queue.add(2);
        //queue.add(1);
        queue.remove();
        queue.remove();
        queue.remove();
        System.out.println(queue.element());
        System.out.println(queue.element());
        System.out.println(queue.element());
    }

    private static void groupPut() throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(2);
        queue.put("i-code.online");
        queue.put("i-code.online");
        queue.put("i-code.online");

    }
}
