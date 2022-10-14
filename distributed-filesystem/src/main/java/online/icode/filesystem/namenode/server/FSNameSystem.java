package online.icode.filesystem.namenode.server;

/**
 * 管理 namenode 元数据的核心组件类
 */
public class FSNameSystem {

    private FSDirectory fsDirectory;

    private FSEditslog fsEditslog;

    public FSNameSystem(){
        this.fsDirectory = new FSDirectory();
        this.fsEditslog = new FSEditslog();
    }

    public Boolean mkdir(String path) {
        //1. 内存中创建目录
        Boolean result = fsDirectory.mkdir(path);
        this.fsEditslog.logEdit("创建目录：" + path);
        //2. edits log 记录
        return true;
    }
}
