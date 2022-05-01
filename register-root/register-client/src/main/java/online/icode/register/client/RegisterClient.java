package online.icode.register.client;

import java.util.UUID;

/**
 * 负责与server 进行通信交互.
 */
public class RegisterClient {

    /**
     *  客户端依赖该组件，当启动该组件后主要做两件事：
     * 1. 将本服务相关信息注册到server中，建立链接通信
     * 2. 与server维持心跳续约
     */

    private String serviceInstanceId;

    public RegisterClient() {
        this.serviceInstanceId = UUID.randomUUID().toString().replace("-", "");
    }


    public void start() {
        /**
         * 启动客户端
         * 1/开启一个线程，注册服务 到server，之后直接while 循环 心跳
         */
        new RegisterClientWorker(serviceInstanceId).start();

    }

}
