package online.icode.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhoucx
 * @time: 2020/11/16 19:10
 */
public class CyclicBarrierTest2 {

    /*
    CyclicBarrier是可重复使用到，也就是每当几个满足是不再等待执行，
    比如公司组织出游，安排了好多辆大把，每坐满一辆就发车，不再等待，类似这种场景，实现如下：
     */

    public static void main(String[] args) {
        //公司人数
        int peopleNum = 2000;
        //每二十五个人一辆车，凑够二十五则发车~
        CyclicBarrier cyclicBarrier = new CyclicBarrier(25,() ->{
            //达到25人出发
            System.out.println("------------25人数凑齐出发------------");
        });

        for (int j = 1; j <= peopleNum; j++) {
            new Thread(new PeopleTask("People-"+j,cyclicBarrier)).start();
        }

    }

    static class PeopleTask implements Runnable{

        private String name;
        private  CyclicBarrier cyclicBarrier;
        public PeopleTask(String name,CyclicBarrier cyclicBarrier){
            this.name = name;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(name+"从家里出发，正在前往聚合地....");
            try {
                TimeUnit.MILLISECONDS.sleep(((int) Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+"到达集合地点，等待其他人..");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


        }
    }
}
