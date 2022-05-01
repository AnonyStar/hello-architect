package onlien.icode.register.server;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 服务存活检测 监控组件.
 */
public class ServiceAliveMonitor {

    private final static Long CHECH_ALIVE_INTERVAL = 60 * 1000L;

    private Daemon daemon;

    public ServiceAliveMonitor () {
        this.daemon = new Daemon();
    }
    public void start() {
        daemon.start();
    }


    private class Daemon  extends Thread{

        private Registry registry = Registry.getInstance();

        @Override
        public void run() {
            while (true) {
                try {
                    Map<String, Map<String, ServiceInstance>> registryRegistry = registry.getRegistry();
                    for (String serviceName : registryRegistry.keySet()) {
                        Map<String, ServiceInstance> stringServiceInstanceMap = registryRegistry.get(serviceName);
                        for (ServiceInstance instance : stringServiceInstanceMap.values()) {
                            if (!instance.isAlive()) {
                                registry.remove(instance.getServiceName(), instance.getServiceInstanceId());
                            }
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(CHECH_ALIVE_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
