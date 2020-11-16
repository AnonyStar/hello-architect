package online.icode.threadlocal;

import javax.naming.ldap.PagedResultsControl;

/**
 * @author: zhoucx
 * @time: 2020/10/2 14:43
 */
public class ThreadLocalTest {


//    private static  Integer num = 0;

    private static ThreadLocal<Integer> num = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {

        Thread[] thread = new Thread[10];

        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(() ->{
                num.set(num.get()+5);
                //num += 5;
                System.out.println(Thread.currentThread()+": num="+ num.get());
            },"Thread-"+i);
        }
        for (Thread thread1 : thread) {
            thread1.start();
        }

    }

}
