package online.icode.jvm.demo;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zhoucx
 * @time: 2022-05-23
 */
public class PigFamily {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入最大的生产数量：");
        final int maxNum = scanner.nextInt();

        //构建共享类
        Cake cake = new Cake(maxNum);

        PigMother1 pigMother = new PigMother1(cake);
        Pig1 sister = new Pig1(cake, "佩奇", 40L);
        Pig1 brother = new Pig1(cake, "乔治", 50L);
       // PigBrother pigBrother = new PigBrother(cake);

        new Thread(pigMother).start();
        new Thread(sister, "佩奇").start();
        new Thread(brother, "乔治").start();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(sister);
        executorService.submit(brother);
    }
}


/**
 * 生产者
 */
class PigMother1 implements Runnable {

    private Cake cake;

    public PigMother1(Cake cake) {
        this.cake = cake;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (cake) {
                if (cake.getMadeNum() >= cake.getMaxNum()) {
                    //说明做完了饼干没有了，不继续了，
                    System.out.println("已经生产完"+ cake.getMaxNum() +"块饼干结束");
                    break;
                }
                //判断当前是否有饼干存在，如果存在则 等待
                if (cake.isFlag()) {
                    try {
                        cake.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                //没有饼干 则进行制作
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                cake.setMadeNum(cake.getMadeNum() +1);
                System.out.println("猪妈妈正在煎第" + cake.getMadeNum() + "块煎饼");
                //表示已经生产了，可以消费了
                cake.setFlag(true);
                //制作好后唤醒消费端消费.
                cake.notifyAll();

            }
        }

    }
}

/**
 * 消费者  佩奇和乔治 本质一样，无需多个类，一个类就可以，多个对象而已
 */
class Pig1 implements Runnable {

    private Cake cake;

    //已经吃了几块
    private int count;

    // 表示 佩奇和 乔治 通过名字区分即可
    private String name;

    private long time;

    public Pig1(Cake cake, String name, long l) {
        this.cake = cake;
        this.name = name;
        time = l;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (cake) {
                //判断 已经做的数量 是否大于 预计的 数量，大于等于则说明制作结束了，
                if (cake.getMadeNum() >= cake.getMaxNum()) {
                    //说明做完了饼干没有了，不继续了，
                    System.out.println(name + "一共吃了" + count + "块煎饼");
                    break;
                }
                //判断是否有饼干, 没有则等待生产
                if (!cake.isFlag()) {
                    System.out.println(name + "等待饼干");
                    try {
                        // 自己进行等待
                        cake.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                //再次判断的意义在于 其他线程调用 notifyAll 会将所有的线程唤醒，想换之间需要约束，所以这里加一次判断，防止两个消费者端互相唤醒，因为 锁对象是一样的，
                if (cake.isFlag()) {
                    // 如果有饼干 即 flag 为 true
                    System.out.println("第" + cake.getMadeNum() + "块" + "猪煎饼" + "正在被" + name+ "吃");
                    //表示没有了
                    cake.setFlag(false);
                    //自己吃的数量记录
                    count++;
                    try {
                        //吃饼干花时间 这个放前面后面无所谓，只是一个耗费时间象征
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //唤醒妈妈去做饼干
                    cake.notifyAll();
                }
            }
        }
    }
}

class Cake {
    //饼干铭城
    private String name;
    //是否存在饼干
    private volatile boolean flag;
    //计划制作的数量
    private int maxNum;
    //当前只做到了第几个
    private volatile int madeNum;

    public Cake(int maxNum) {
        this.maxNum = maxNum;
        this.flag = false; //默认没饼干
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getMadeNum() {
        return madeNum;
    }

    public void setMadeNum(int madeNum) {
        this.madeNum = madeNum;
    }
}