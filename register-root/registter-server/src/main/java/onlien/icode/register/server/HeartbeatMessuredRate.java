package onlien.icode.register.server;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * 心跳计数测量器
 *
 * @author xiaoz
 */
public class HeartbeatMessuredRate {


    private static HeartbeatMessuredRate instance = new HeartbeatMessuredRate();

    //最近一秒的心跳次数
    private LongAdder latestMinuteHeartbeatRate = new LongAdder();

    //最后一分钟的时间戳
    private long latestMinuteTimestamp = System.currentTimeMillis();

    private HeartbeatMessuredRate() {
        Daemon daemon = new Daemon();
        daemon.setDaemon(true);
        daemon.start();
    }


    /**
     * 增加最近一分钟的心跳次数 总的
     */
    public synchronized void increment() {

        if (System.currentTimeMillis() - latestMinuteTimestamp > 60 * 1000) {
            latestMinuteHeartbeatRate = new LongAdder();
            latestMinuteTimestamp = System.currentTimeMillis();
        }

        latestMinuteHeartbeatRate.increment();
    }

    public static HeartbeatMessuredRate getInstance() {

        return instance;
    }


    public long getLatestMinuteHeartbeatRate() {
        return latestMinuteHeartbeatRate.longValue();
    }

    private class Daemon extends Thread {
        @Override
        public void run() {
            while (true) {
                try {

                    synchronized (SelfProtectionPolicy.class) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - latestMinuteTimestamp > 60 * 1000) {
                            latestMinuteHeartbeatRate = new LongAdder();
                            latestMinuteTimestamp = System.currentTimeMillis();
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
