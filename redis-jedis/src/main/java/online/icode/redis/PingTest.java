package online.icode.redis;

import redis.clients.jedis.Jedis;

/**
 * @author: zhoucx
 * @time: 2021/2/22 15:18
 */
public class PingTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.56.10",6379);
        System.out.println(jedis.ping());
    }
}
