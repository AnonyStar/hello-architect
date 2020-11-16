package online.icode.thread.vl;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/10/26 10:52
 */
public class App {

    public volatile static boolean flag = true;

    public static void main(String[] args) {

        new Thread(() ->{
            int num = 0;
            while (flag){
                num ++;
            }
            System.out.println("num:" +num);

        }).start();
        System.out.println("线程启动");
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            flag = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
