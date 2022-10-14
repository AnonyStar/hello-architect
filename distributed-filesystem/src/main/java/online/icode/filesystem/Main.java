package online.icode.filesystem;

import online.icode.filesystem.namenode.server.FSNameSystem;
import online.icode.filesystem.namenode.server.NameNode;
import online.icode.filesystem.namenode.server.NameNodeRpcServer;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        NameNode nameNode = new NameNode();
        nameNode.start();
    }


    @Test
    public void mkdir() throws InterruptedException {
        NameNodeRpcServer rpcServer = new NameNodeRpcServer(new FSNameSystem());

        String path = "/usr/local/";

        for (int i = 0; i < 10; i++) {
            String paths = path + i;
            Mkdir mkdir = new Mkdir(paths, rpcServer);
            mkdir.start();
        }

        while (true) {
            TimeUnit.SECONDS.sleep(10);
        }
    }


    public static class Mkdir extends Thread {

        private String path;
        NameNodeRpcServer rpcServer;
        public Mkdir(String path, NameNodeRpcServer rpcServer) {
            this.rpcServer = rpcServer;
            this.path = path;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                rpcServer.mkdir(path + "/" + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}