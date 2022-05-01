package online.icode.register.client;

import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpRequest;

/**
 * 负责发送各种http的组件.
 */
public class HttpSender {


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


}
