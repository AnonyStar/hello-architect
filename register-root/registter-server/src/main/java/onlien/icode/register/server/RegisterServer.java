package onlien.icode.register.server;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RegisterServer {


    public static void main(String[] args) throws InterruptedException {
        //1. 模拟一个请求注册服务
        String serviceInstanceId = UUID.randomUUID().toString().replace("-", "");
        RegisterRequest request = new RegisterRequest();
        request.setIp("192.168.11.121");
        request.setPort(90000);
        request.setHostName("inventory-01");
        request.setServiceName("inventory-service");
        request.setServiceInstanceId(serviceInstanceId);

        RegisterServerController controller = new RegisterServerController();

        controller.register(request);

        //2. 模拟一个心跳检测
        HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
        heartbeatRequest.setServiceInstanceId(serviceInstanceId);
        heartbeatRequest.setServiceName("inventory-service");
        controller.heartbeat(heartbeatRequest);


        //3. 后台开启一个线程 检测所有实例服务的存在状态.
        ServiceAliveMonitor serviceAliveMonitor = new ServiceAliveMonitor();
        serviceAliveMonitor.start();

        while (true) {
            TimeUnit.SECONDS.sleep(30);
        }
    }
}
