package online.icode.redis;

import redis.clients.jedis.Jedis;

/**
 * @author: zhoucx
 * @time: 2021/2/22 15:18
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.56.10",6379);
        jedis.auth("root"); //如果开启了密码验证
        jedis.connect(); //链接
        jedis.disconnect(); //断开链接
        jedis.flushDB(); //情况当前库
        jedis.flushAll(); //清空所有库
    }
}
