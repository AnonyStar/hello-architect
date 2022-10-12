package onlien.icode.register.server;

/**
 * 自我保护机制.
 */
public class SelfProtectionPolicy {

    /**
     * 维护预期的 每分钟心跳数 与阈值数
     * 单例存在
     */


    private static SelfProtectionPolicy instance = new SelfProtectionPolicy();


    /**
     * 期望的 每分钟心跳次数， 如果10个实列 则心跳次数为 10*2 = 20
     */
    private long expectedHeartbeatRate = 0L;

    /**
     *期望心跳次数的阈值，默认为 0.85 * 10*2 = 17，则每分钟至少要有17次心跳才不会进入自我保护程序.
     */

    private long expectedHeartbeatThreshold = 0L;



    public static SelfProtectionPolicy getInstance() {
        return instance;
    }

    public long getExpectedHeartbeatRate() {
        return expectedHeartbeatRate;
    }

    public void setExpectedHeartbeatRate(long expectedHeartbeatRate) {
        this.expectedHeartbeatRate = expectedHeartbeatRate;
    }

    public long getExpectedHeartbeatThreshold() {
        return expectedHeartbeatThreshold;
    }

    public void setExpectedHeartbeatThreshold(long expectedHeartbeatThreshold) {
        this.expectedHeartbeatThreshold = expectedHeartbeatThreshold;
    }

    public boolean isEnable() {

        HeartbeatMessuredRate heartbeatMessuredRate = HeartbeatMessuredRate.getInstance();

        if (heartbeatMessuredRate.getLatestMinuteHeartbeatRate() < expectedHeartbeatThreshold) {
            System.out.println("【自我保护机制开启】最近一分钟心跳次数=" + heartbeatMessuredRate.getLatestMinuteHeartbeatRate() + ", 期望心跳次数=" + this.expectedHeartbeatThreshold);
            return true;
        }
        System.out.println("【自我保护机制未开启】最近一分钟心跳次数=" + heartbeatMessuredRate.getLatestMinuteHeartbeatRate() + ", 期望心跳次数=" + this.expectedHeartbeatThreshold);

        return false;

    }
}
