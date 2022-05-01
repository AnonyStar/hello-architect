package onlien.icode.register.server;

import lombok.Data;

@Data
public class ServiceInstance {

    private final static Long NOT_ALIVE_PERIOD = 90 * 1000L;
    private String serviceName;

    private String ip;

    private String hostName;

    private Integer port;

    private String serviceInstanceId;

    private Lease lease;


    public ServiceInstance() {
        this.lease = new Lease();
    }
    /**
     * 服务续约.
     */
    public void renew() {
        this.lease.renew();
    }

    public boolean isAlive() {
        return this.lease.isAlive();
    }


    /**
     * 服务契约.
     */
    private class Lease {
        /**
         * 最后一次心跳的时间.
         */
        private Long latesHeartbeatTime = System.currentTimeMillis();

        /**
         * 续约
         */
        public void renew() {
            System.out.println("服务实例【" + serviceInstanceId + "】，进行了续约：" + latesHeartbeatTime);
            latesHeartbeatTime = System.currentTimeMillis();
        }

        public Boolean isAlive() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - latesHeartbeatTime > NOT_ALIVE_PERIOD) {
                System.out.println("服务实例【" + serviceInstanceId + "】，不再存活....");
                return false;
            }
            System.out.println("服务实例【" + serviceInstanceId + "】，保持存活....");
            return true;
        }

    }
}
