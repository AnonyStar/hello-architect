package online.icode.filesystem.namenode.server;

import java.util.concurrent.TimeUnit;

/**
 * NameNode 核心启动类.
 *  1.启动 rpc服务
 */
public class NameNode {
    /**
     * NameNode 是否在运行.
     */
    private volatile Boolean shouldRun;

    /**
     * 对外提供的rpc接口组件服务,
     */
    private NameNodeRpcServer rpcServer;

    public NameNode() {
        shouldRun = true;
        this.rpcServer = new NameNodeRpcServer(new FSNameSystem());
    }


    private void run() {
        this.rpcServer.start();

        while (shouldRun) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void start() {
        run();
    }
}
