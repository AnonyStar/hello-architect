package online.icode.register.client;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 负责与server 进行通信交互.
 */
public class RegisterClient {

    /**
     *  客户端依赖该组件，当启动该组件后主要做两件事：
     * 1. 将本服务相关信息注册到server中，建立链接通信
     * 2. 与server维持心跳续约
     */

    private String serviceInstanceId;
    private HttpSender httpSender;
    private Boolean isRunning;

    private HeartbeatWorker heartbeatWorker;

    private ClientCacheServiceRegistry clientCacheServiceRegistry;

    private final static String IP = "192.168.10.121";

    private final static String HOSTNAME = "inventory-01";
    private final static String SERVICENAME = "inventory-service";
    private final static Integer PORT = 8900;

    private final static Integer HEARTBEAT_INTERVAL = 5;


    public RegisterClient() {
        this.serviceInstanceId = UUID.randomUUID().toString().replace("-", "");
        this.httpSender = new HttpSender();
        this.heartbeatWorker = new HeartbeatWorker();
        this.isRunning = true;
        this.clientCacheServiceRegistry = new ClientCacheServiceRegistry(httpSender, isRunning);
    }


    public void start() {
        /**
         * 启动客户端
         * 客户端主要两个功能点 :  1. 将自身消息注册到 server 中， 2. 开启一个心跳检测机制，每隔30秒发送一次心跳消息
         *
         */
        try {
            RegisterWorker registerWorker = new RegisterWorker();
            registerWorker.start();
            registerWorker.join();

            //启动心跳线程
            heartbeatWorker.start();

            //启动本地服务注册表缓存表
            clientCacheServiceRegistry.initialize();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * 停止 心跳组件 线程运行.
     */
    public void shutdown() {
        this.isRunning = false;
        this.heartbeatWorker.interrupt();
        this.clientCacheServiceRegistry.destroy();
        this.httpSender.cancel(SERVICENAME, serviceInstanceId);
    }


    /**
     * 注册工作组件线程
     */
    private class RegisterWorker extends Thread {

        @Override
        public void run() {
            // 1. 构建请求报文 包含： 服务名  IP  端口链接 主机名称
            RegisterRequest request = new RegisterRequest();
            request.setIp(IP);
            request.setPort(PORT);
            request.setHostName(HOSTNAME);
            request.setServiceName(SERVICENAME);
            request.setServiceInstanceId(serviceInstanceId);
            //2.发送请求
            RegisterResponse registerResponse = httpSender.register(request);
            System.out.println("服务：【" + SERVICENAME + "】注册结果：" + registerResponse.getStatus() + "......");
        }
    }


    /**
     * 心跳组件线程
     */
    private class HeartbeatWorker extends Thread {

        @Override
        public void run() {
            //4. 开启死循环进行心跳检测机制.
            HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
            heartbeatRequest.setServiceInstanceId(serviceInstanceId);
            while (isRunning) {
                try {
                    HeartbeatResponse heartbeatResponse = httpSender.heartbeat(heartbeatRequest);
                    System.out.println("服务【serviceInstanceId:" + serviceInstanceId + "】心跳结果：" + heartbeatResponse.getStatus() + "......");
                    TimeUnit.SECONDS.sleep(HEARTBEAT_INTERVAL);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("心跳检测异常-终止.");
                }
            }
        }
    }

}
