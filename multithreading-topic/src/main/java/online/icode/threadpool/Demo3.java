package online.icode.threadpool;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/10/30 15:34
 */
public class Demo3 {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        // 1. 延迟一定时间执行一次
        service.schedule(() ->{
            System.out.println("schedule ==> 云栖简码-i-code.online");
        },2, TimeUnit.SECONDS);

        // 2. 按照固定频率周期执行
        service.scheduleAtFixedRate(() ->{
            System.out.println("scheduleAtFixedRate ==> 云栖简码-i-code.online");
        },2,3,TimeUnit.SECONDS);

        //3. 按照固定频率周期执行
        service.scheduleWithFixedDelay(() -> {
            System.out.println("scheduleWithFixedDelay ==> 云栖简码-i-code.online");
        },2,5,TimeUnit.SECONDS);

    }
}
