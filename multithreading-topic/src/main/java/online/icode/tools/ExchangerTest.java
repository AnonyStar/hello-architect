package online.icode.tools;

import java.util.Objects;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/11/17 19:13
 */
public class ExchangerTest {

    /*
    Exchanger 交换， 用于线程间协作的工具类，可以交换线程间的数据，
    其提供一个同步点，当线程到达这个同步点后进行数据间的交互，遗传算法可以如此来实现，
    以及校对工作也可以如此来实现
     */

    public static void main(String[] args) {
        /*
        模拟 两个工作人员录入记录，为了防止错误，两者录的相同内容，程序仅从校对，看是否有错误不一致的
         */

        //开辟两个容量的线程池
        final ExecutorService service = Executors.newFixedThreadPool(2);

        Exchanger<InfoMsg> exchanger = new Exchanger<>();

        service.submit(() ->{
            //模拟数据 线程 A的
            InfoMsg infoMsg = new InfoMsg();
            infoMsg.content="这是线程A";
            infoMsg.id ="10001";
            infoMsg.desc = "1";
            infoMsg.message = "message";
            System.out.println("正在执行其他...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                final InfoMsg exchange = exchanger.exchange(infoMsg);
                System.out.println("线程A 交换数据====== 得到"+ exchange);
                if (!exchange.equals(infoMsg)){
                    System.out.println("数据不一致~~请稽核");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.submit(() ->{
            //模拟数据 线程 A的
            InfoMsg infoMsg = new InfoMsg();
            infoMsg.content="这是线程A";
            infoMsg.id ="10001";
            infoMsg.desc = "1";
            infoMsg.message = "message";
            System.out.println("正在执行其他...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                final InfoMsg exchange = exchanger.exchange(infoMsg);
                System.out.println("线程B 交换数据====== 得到"+ exchange);
                if (!exchange.equals(infoMsg)){
                    System.out.println("数据不一致~~请稽核");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.shutdown();
    }


    static class InfoMsg{
        String id;
        String name;
        String message;
        String content;
        String desc;

        @Override
        public String toString() {
            return "InfoMsg{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", message='" + message + '\'' +
                    ", content='" + content + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            InfoMsg infoMsg = (InfoMsg) o;
            return Objects.equals(id, infoMsg.id) &&
                    Objects.equals(name, infoMsg.name) &&
                    Objects.equals(message, infoMsg.message) &&
                    Objects.equals(content, infoMsg.content) &&
                    Objects.equals(desc, infoMsg.desc);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, message, content, desc);
        }
    }
}
