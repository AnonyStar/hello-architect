package online.icode.register.client;

import lombok.Data;

import java.rmi.dgc.Lease;
@Data
public class ServiceInstance {
    private String serviceName;

    private String ip;

    private String hostName;

    private Integer port;

    private String serviceInstanceId;

    private Lease lease;
}
