package onlien.icode.register.server;

import lombok.Data;

@Data
public class HeartbeatRequest {
    private String serviceInstanceId;

    private String serviceName;
}
