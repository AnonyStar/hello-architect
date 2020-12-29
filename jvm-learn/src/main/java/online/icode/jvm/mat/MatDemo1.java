package online.icode.jvm.mat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/12/21 10:05
 */
public class MatDemo1 {

    /*
    1.执行代码
    2. 通过 jmap 命令获取堆快照
        jmap -dump:live,format=b,file=dump.hprof 15268
    3. 通过 mat 工具分析文件
     */

    public static void main(String[] args) throws InterruptedException {
        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            dataList.add(new Data());
        }
        TimeUnit.SECONDS.sleep(60*60);
        
    }
    
    static class Data{}
}
