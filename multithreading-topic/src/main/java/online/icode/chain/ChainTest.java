package online.icode.chain;

import online.icode.chain.v1.IDoProcesser;
import online.icode.chain.v1.PrintProcesser;
import online.icode.chain.v1.RequestProcesser;
import online.icode.chain.v1.SaveProcesser;
import online.icode.chain.v2.PrintProcesserv2;
import online.icode.chain.v2.RequestProcesserv2;
import online.icode.chain.v2.SaveProcesserv2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhoucx
 * @time: 2020/10/3 9:53
 */
public class ChainTest {



    @Test
    public void chainTest01(){

        IDoProcesser prinProcesser = new PrintProcesser(null);
        IDoProcesser saveProcesser = new SaveProcesser(prinProcesser);
        IDoProcesser requestProcesser = new RequestProcesser(saveProcesser);
        Request request = new Request();
        request.setName("测试数据");
        List<Request> requests = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            requests.add(new Request("请求--"+i));
        }
        requests.forEach(requestProcesser::process);
    }

    @Test
    public void chainTest02(){


    }

    public static void main(String[] args) {
        PrintProcesserv2 prinProcesser = new PrintProcesserv2(null);
        SaveProcesserv2 saveProcesser = new SaveProcesserv2(prinProcesser);
        RequestProcesserv2 requestProcesser = new RequestProcesserv2(saveProcesser);
        prinProcesser.start();
        saveProcesser.start();
        requestProcesser.start();
        List<Request> requests = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            requests.add(new Request("请求--"+i));
        }
        requests.forEach(requestProcesser::process);
    }
}
