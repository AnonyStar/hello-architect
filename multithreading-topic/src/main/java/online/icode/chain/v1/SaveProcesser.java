package online.icode.chain.v1;

import online.icode.chain.Request;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/10/3 9:42
 */
public class SaveProcesser  implements IDoProcesser {

    private IDoProcesser nextProcesser;

    public SaveProcesser(IDoProcesser nextProcesser) {
        this.nextProcesser = nextProcesser;
    }

    @Override
    public void process(Request request) {
        System.out.println("SaveProcesser ----> 处理请求："+request);
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nextProcesser.process(request);
    }
}
