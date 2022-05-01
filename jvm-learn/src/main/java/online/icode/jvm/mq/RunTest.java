package online.icode.jvm.mq;


import java.net.InetSocketAddress;


public class RunTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RPCServer rpcServer = new RPCServer();
                rpcServer.register(Tinterface.class, TinterfaceImpl.class);
                rpcServer.start(10000);
            }
        }).start();
        Tinterface tinterface = RPCclient.getRemoteProxyObj(Tinterface.class, new InetSocketAddress("localhost", 10000));
        System.out.println(tinterface.send("rpc 测试用例"));

    }
}