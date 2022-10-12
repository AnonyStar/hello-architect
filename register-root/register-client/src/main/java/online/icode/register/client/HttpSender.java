package online.icode.register.client;

import java.util.HashMap;
import java.util.Map;

/**
 * 负责发送各种http的组件.
 */
public class HttpSender {



    private final static String IP = "192.168.10.121";

    private final static String HOSTNAME = "inventory-01";
    private final static String SERVICENAME = "inventory-service";
    private final static Integer PORT = 8900;
    /**
     * 注册请求.
     *
     * @param request
     * @return
     */
    public RegisterResponse register(RegisterRequest request) {
        System.out.println("服务实例【" + request + "】，发送请求进行注册...");

//        HttpRequest httpRequest = new BasicHttpRequest()
//        HttpClients.createDefault().execute()
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setStatus(RegisterResponse.SUCCESS);
        return registerResponse;
    }

    /**
     * 心跳检测请求.
     *
     * @param request
     * @return
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest request) {
        System.out.println("服务实例【" + request + "】，发送心跳请求....");
        HeartbeatResponse response = new HeartbeatResponse();
        response.setStatus(HeartbeatResponse.SUCCESS);
        return response;
    }


    /**
     * 拉取所有的注册表信息.
     * @return
     */
    public Map<String, Map<String, ServiceInstance>> fetchServiceRegistry() {

        Map<String, Map<String, ServiceInstance>> registry = new HashMap<>();

        ServiceInstance serviceInstance = new ServiceInstance();
        serviceInstance.setServiceInstanceId("FINANCE-SERVICE-192.168.31.207:9000");
        serviceInstance.setIp(IP);
        serviceInstance.setPort(PORT);
        serviceInstance.setHostName(HOSTNAME);
        serviceInstance.setServiceName(SERVICENAME);
        Map<String, ServiceInstance> serviceInstanceMap = new HashMap<>();
        serviceInstanceMap.put(serviceInstance.getServiceInstanceId(), serviceInstance);

        registry.put(serviceInstance.getServiceName(), serviceInstanceMap);

        return registry;
    }

    /**
     * 服务下线.
     * @param servicename
     * @param serviceInstanceId
     */
    public void cancel(String servicename, String serviceInstanceId) {

        System.out.println("服务实列下线：" + servicename + " :" + serviceInstanceId);


    }
}
