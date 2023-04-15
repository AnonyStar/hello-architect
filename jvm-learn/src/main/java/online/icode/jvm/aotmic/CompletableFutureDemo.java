package online.icode.jvm.aotmic;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * @author zhoucx
 * @version 1.0.0
 * @ClassName CompletableFutureDemo.java
 * @Description TODO
 * @createTime 2023年04月10日 21:44:00
 */
public class CompletableFutureDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 60,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>());

        //m1(poolExecutor);
        //m2(poolExecutor);


        poolExecutor.shutdown();

    }

    private static void m2(ThreadPoolExecutor poolExecutor) {
        System.out.println(CompletableFuture.supplyAsync(() -> {
            //计算数据
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("计算查询服务-得到结果：5");
            return 5;
        }, poolExecutor).thenApply(r -> {
            //            int c = 1/0;
            return r + 5;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("result==" + v);
            }
        }).exceptionally(e -> {
            System.out.println("出现了异常：" + e.getMessage());
            e.printStackTrace();
            return 0;
        }).thenAccept(v -> {
            System.out.println("当前的v的值是什么" + v);
        }).join());


        System.out.println("main ---over -----");
    }

    private static void m1(ThreadPoolExecutor poolExecutor) throws InterruptedException, ExecutionException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-----come in");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(completableFuture.get());

        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "------come in");
        }, poolExecutor);
        System.out.println(completableFuture1.get());

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "----come in");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 5;
        });
        System.out.println(completableFuture2.get());
    }
}
