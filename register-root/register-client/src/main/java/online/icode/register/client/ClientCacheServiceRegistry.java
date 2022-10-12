package online.icode.register.client;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 客户端缓存的 服务注册表
 * 1. 缓存注册服务表
 * 2. 定时去更新缓存表
 */
public class ClientCacheServiceRegistry {


    /**
     * 缓存表本地 拉取时间间隔周期
     */
    private static final Long SERVICE_REGISTRY_FETCH_INTERVAL = 8 * 1000L;


    /**
     * 缓存 注册表
     */
    private Map<String, Map<String, ServiceInstance>> registry = new HashMap<>();


    //定时刷新线程工作
    private FetchDaemonWorker fetchDaemonWorker;
    private HttpSender httpSender;
    private Boolean isRunning;

    public ClientCacheServiceRegistry(HttpSender httpSender, Boolean isRunning) {
        this.fetchDaemonWorker = new FetchDaemonWorker();
        this.httpSender = httpSender;
        this.isRunning = isRunning;
    }

    //初始化
    public void initialize(){
        fetchDaemonWorker.start();
    }


    //销毁 定时刷新注册表组件
    public void destroy(){
        fetchDaemonWorker.interrupt();
    }





    private class FetchDaemonWorker extends Thread{

        @Override
        public void run() {
            //定时拉取注册表
            while (isRunning) {
                //运行中的
                try {
                    registry = httpSender.fetchServiceRegistry();
                    System.out.println("获取缓存注册表：" + registry);
                    TimeUnit.MILLISECONDS.sleep(SERVICE_REGISTRY_FETCH_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
