package online.icode.thread.safety;

/**
 * @url: i-code.online
 * @author: AnonyStar
 * @time: 2020/10/14 15:39
 */
public class ThreadSafaty {
    //共享变量
    static int count = 0;

    public static void main(String[] args) {

        //创建线程
        Runnable runnable = () -> {
            synchronized (ThreadSafaty.class){
                for (int i = 0; i < 5; i++) {
                    count ++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

        for (int i = 0; i < 100; i++) {
            new Thread(runnable,"Thread-"+i).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count = "+ count);
    }
}
