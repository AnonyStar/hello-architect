package online.icode.register.client;

import java.util.concurrent.TimeUnit;

/**
 * 负责向server 发起注册请求的 线程.
 */
public class RegisterClientWorker extends Thread{

    private final static String IP = "192.168.10.121";

    private final static String HOSTNAME = "inventory-01";
    private final static String SERVICENAME = "inventory-service";

    private final static Integer PORT = 8900;
    /**
     * 服务实例id
     */
    private String serviceInstanceId;

    //标记是否已注册完成.
    private Boolean finishedRegister;

    /**
     * http请求组件.
     */
    private HttpSender httpSender;


    public RegisterClientWorker(String serviceInstanceId) {
        this.httpSender = new HttpSender();
        this.finishedRegister = false;
        this.serviceInstanceId = serviceInstanceId;
    }

    @Override
    public void run() {
        if (!finishedRegister) {
            // 1. 构建请求报文 包含： 服务名  IP  端口链接 主机名称
            RegisterRequest request = new RegisterRequest();
            request.setIp(IP);
            request.setPort(PORT);
            request.setHostName(HOSTNAME);
            request.setServiceName(SERVICENAME);
            request.setServiceInstanceId(serviceInstanceId);
            //2.发送请求
            RegisterResponse registerResponse = httpSender.register(request);
            //3. 注册成功则标记状态
            if (registerResponse.getStatus().equals(RegisterResponse.SUCCESS)) {
                this.finishedRegister = true;
            } else {
                return;
            }
        }
        //4. 开启死循环进行心跳检测机制.
        HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
        heartbeatRequest.setServiceInstanceId(serviceInstanceId);
        while (true) {
            try {
                HeartbeatResponse heartbeatResponse = httpSender.heartbeat(heartbeatRequest);
                System.out.println("服务【serviceInstanceId:" + serviceInstanceId + "】心跳结果：" + heartbeatResponse.getStatus() + "......");
                TimeUnit.SECONDS.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("心跳检测异常.");
            }
        }
    }



}
