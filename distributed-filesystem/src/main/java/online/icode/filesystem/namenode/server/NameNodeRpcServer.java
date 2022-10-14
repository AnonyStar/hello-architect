package online.icode.filesystem.namenode.server;

/**
 * NameNode 提供的rpc能力服务.
 */
public class NameNodeRpcServer {

    private FSNameSystem fsNameSystem;


    public NameNodeRpcServer(FSNameSystem fsNameSystem) {
        this.fsNameSystem = fsNameSystem;
    }

    public void start() {
        System.out.println("NameNode-启动Rpc服务，监听端口");
    }


    /**
     * 创建目录
     * @param path
     * @return
     */
    public Boolean mkdir(String path) {
        return this.fsNameSystem.mkdir(path);
    }
}
