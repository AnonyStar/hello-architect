package onlien.icode.register.server;

public class RegisterServerController {

    private Registry registry = Registry.getInstance();

    /**
     * 服务注册.
     * @param request
     */
    public RegisterResponse register(RegisterRequest request) {
        RegisterResponse registerResponse = new RegisterResponse();

        try {
            ServiceInstance serviceInstance =new ServiceInstance();
            serviceInstance.setIp(request.getIp());
            serviceInstance.setServiceInstanceId(request.getServiceInstanceId());
            serviceInstance.setServiceName(request.getServiceName());
            serviceInstance.setHostName(request.getHostName());
            serviceInstance.setPort(request.getPort());
            registry.register(serviceInstance);

            registerResponse.setStatus(RegisterResponse.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            registerResponse.setStatus(RegisterResponse.FAILURE);
        }
        return registerResponse;
    }

    /**
     * 心跳检测
     *
     * @param heartbeatRequest
     * @return
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) {
        HeartbeatResponse response = new HeartbeatResponse();

        try {
            ServiceInstance serviceInstance =  registry.getServiceInstance(heartbeatRequest.getServiceInstanceId(), heartbeatRequest.getServiceName());
            //续约
            serviceInstance.renew();
            response.setStatus(HeartbeatResponse.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HeartbeatResponse.FAILURE);
        }
        return response;

    }
}
