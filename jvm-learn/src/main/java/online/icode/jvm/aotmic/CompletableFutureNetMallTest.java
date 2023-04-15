package online.icode.jvm.aotmic;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhoucx
 * @version 1.0.0
 * @ClassName CompletableFutureNetMallDemo.java
 * @Description TODO
 * @createTime 2023年04月10日 22:35:00
 */
public class CompletableFutureNetMallTest {


    static List<NetMall> list = Arrays.asList(
            new NetMall("taobao"),
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("pdd"),
            new NetMall("tmall")
    );

    public static List<String> getPriceBySync(List<NetMall> list, String productName) {
        return list.stream().map(netMall -> String.format("%s is %.2f price in %s", productName, netMall.getPrice(productName), netMall.getNetMallName())).collect(Collectors.toList());
    }

    public static List<String> getPriceByAsync(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall -> CompletableFuture.supplyAsync(() -> String.format("%s is %.2f price in %s", productName, netMall.getPrice(productName), netMall.getNetMallName())))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> mysql = getPriceBySync(list, "mysql");
        mysql.forEach(s -> System.out.println(s));
        System.out.println("耗时====" + (System.currentTimeMillis() - startTime));

        long startTime2 = System.currentTimeMillis();
        List<String> mysql1 = getPriceByAsync(list, "mysql");
        mysql1.forEach(System.out::println);
        System.out.println("耗时====" + (System.currentTimeMillis() - startTime2) );

    }

    @Getter
    private static class NetMall {
        private String netMallName;

        public NetMall(String netMallName) {
            this.netMallName = netMallName;
        }

        public double getPrice(String productName) {
            //获取价格
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return ThreadLocalRandom.current().nextDouble() * 10 + productName.charAt(0);
        }
    }
}
