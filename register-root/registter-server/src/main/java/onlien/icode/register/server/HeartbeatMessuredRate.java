package onlien.icode.register.server;

import java.util.concurrent.TimeUnit;

/**
 * 心跳计数测量器
 *
 * @author xiaoz
 */
public class HeartbeatMessuredRate {


    private static HeartbeatMessuredRate instance = new HeartbeatMessuredRate();

    //最近一秒的心跳次数
    private long latestMinuteHeartbeatRate = 0L;

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
            latestMinuteHeartbeatRate = 0L;
            latestMinuteTimestamp = System.currentTimeMillis();
        }

        latestMinuteHeartbeatRate++;
    }

    public static HeartbeatMessuredRate getInstance() {

        return instance;
    }


    public long getLatestMinuteHeartbeatRate() {
        return latestMinuteHeartbeatRate;
    }

    private class Daemon extends Thread {
        @Override
        public void run() {
            while (true) {
                try {

                    synchronized (SelfProtectionPolicy.class) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - latestMinuteTimestamp > 60 * 1000) {
                            latestMinuteHeartbeatRate = 0L;
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
