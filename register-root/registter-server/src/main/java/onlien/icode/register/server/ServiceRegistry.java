package onlien.icode.register.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 注册中心服务端 注册表
 */
public class ServiceRegistry {


    /**
     * 注册表单例.
     */
    private static ServiceRegistry instance = new ServiceRegistry();


    /**
     * 存放注册表的数据结构.
     * Map  key 为服务名称， map<String, ServiceInstance> 作为所有服务的实例
     * map<String, ServiceInstance>  中 key  作为服务实列id  value 作为服务实例信息.
     */
    private Map<String, Map<String, ServiceInstance>> registry =
            new HashMap<>();
    private ServiceRegistry() {

    }


    public static ServiceRegistry getInstance() {
        return instance;
    }

    public void register(ServiceInstance serviceInstance) {
        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceInstance.getServiceName());

        if (Objects.isNull(serviceInstanceMap)) {
            serviceInstanceMap = new HashMap<>();
            registry.put(serviceInstance.getServiceName(), serviceInstanceMap);
        }

        serviceInstanceMap.put(serviceInstance.getServiceInstanceId(), serviceInstance);

        System.out.println("服务实例【" +serviceInstance + "】,完成注册....");
        System.out.println("当前注册表:" + registry);

        //更新自我保护机制的阈值
        synchronized(SelfProtectionPolicy.class) {
            SelfProtectionPolicy protectionPolicy = SelfProtectionPolicy.getInstance();
            protectionPolicy.setExpectedHeartbeatRate(protectionPolicy.getExpectedHeartbeatRate() + 2);
            protectionPolicy.setExpectedHeartbeatThreshold((long) (protectionPolicy.getExpectedHeartbeatRate() * 0.85));
        }
    }

    /**
     * 获取服务实例.
     * @param serviceInstanceId
     * @param serviceName
     * @return
     */
    public ServiceInstance getServiceInstance(String serviceInstanceId, String serviceName) {
        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
        ServiceInstance serviceInstance = serviceInstanceMap.get(serviceInstanceId);
        return serviceInstance;
    }

    /**
     * 获取整个注册表.
     * @return
     */
    public Map<String, Map<String, ServiceInstance>> getRegistry() {
        return registry;
    }

    /**
     * 从注册表中删除一个服务实例.
     * @param serviceName
     * @param serviceInstanceId
     */
    public void remove(String serviceName, String serviceInstanceId) {
        System.out.println("服务实列【" + serviceInstanceId + "】，从注册表中删除！");
        Map<String, ServiceInstance> stringServiceInstanceMap = registry.get(serviceName);
        stringServiceInstanceMap.remove(serviceInstanceId);
        //更新自我保护机制的阈值
        synchronized(SelfProtectionPolicy.class) {
            SelfProtectionPolicy protectionPolicy = SelfProtectionPolicy.getInstance();
            protectionPolicy.setExpectedHeartbeatRate(protectionPolicy.getExpectedHeartbeatRate() - 2);
            protectionPolicy.setExpectedHeartbeatThreshold((long) (protectionPolicy.getExpectedHeartbeatRate() * 0.85));
        }
    }


}
