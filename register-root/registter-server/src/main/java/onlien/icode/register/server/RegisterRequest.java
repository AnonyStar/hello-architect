package onlien.icode.register.server;

import lombok.Data;

@Data
public class RegisterRequest {
    private String serviceName;

    private String ip;

    private String hostName;

    private Integer port;

    private String serviceInstanceId;
}
