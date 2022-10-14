package online.icode.filesystem.namenode.server;

import java.util.LinkedList;
import java.util.List;

/**
 * 内存中文件目录维护核心组件.
 */
public class FSDirectory {


    private NodeDirectory rootDir;

    public FSDirectory() {
        rootDir = new NodeDirectory("/");
    }

    public Boolean mkdir(String path) {
        //path = /usr/warehouse/hive
        // 按层级来创建目录，是一个链表结构.
/*        String[] paths = path.split("/");
        for (String patch : paths) {
            if (!patch.isEmpty()) {

            }
        }*/
        System.out.println("【内存文件目录】-创建......" + path);

        return true;
    }


    private Boolean mkdir(NodeDirectory paretDir, String dir){
        NodeDirectory directory = new NodeDirectory(dir);
        paretDir.addChildren(directory);
        return true;
    }


    /**
     * 目录树中的节点
     */

    private interface  INode {


    }


    public static class  NodeDirectory implements INode {

        private String path;

        private List<INode> children;

        public NodeDirectory(String path) {
            this.path = path;
            this.children = new LinkedList<>();

        }


        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<INode> getChildren() {
            return children;
        }

        public void addChildren(INode children) {
            this.children.add(children);
        }
    }


    public static class NodeFile implements INode {

        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
