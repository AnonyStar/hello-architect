package online.icode.redis;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @author: AnonyStar
 * @time: 2021/2/22 15:32
 */
public class RedisStringTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.56.10", 6379);
        jedis.flushDB();
        System.out.println("=========增加数据==========");
        System.out.println(jedis.set("key1","value1"));
        System.out.println(jedis.set("key2","value2"));
        System.out.println(jedis.set("key3","value3"));
        System.out.println("=========删除数据==========");
        System.out.println(jedis.del("key2"));
        System.out.println(jedis.get("key2"));
        System.out.println("=========修改数据==========");
        System.out.println("修改key1的值：" + jedis.set("key1", "modify value1"));
        System.out.println(jedis.get("key1"));
        System.out.println("在 key3 后面追加的内容：" + jedis.append("key3", "追加的内容"));
        System.out.println("获取key3的值：" + jedis.get("key3"));
        System.out.println("=========批量操作数据==========");
        System.out.println("批量添加元素：" + jedis.mset("key4", "value4", "key5", "value5"));
        System.out.println("批量获取元素：" + jedis.mget("key1", "key4", "key5"));
        System.out.println("批量删除元素： " + jedis.del("key1", "key2"));
        System.out.println("批量获取元素：" + jedis.mget("key1", "key2", "key5"));

        System.out.println("==============新增键值对防止覆盖原先值==============");
        System.out.println("清空数据库：" + jedis.flushDB());
        System.out.println("添加元素setnx：" + jedis.setnx("key1","value1"));
        System.out.println("添加元素setnx：" + jedis.setnx("key1","value111"));
        System.out.println("添加元素setnx：" + jedis.setnx("key2","value2"));
        System.out.println("获取元素：" + jedis.mget("key1", "key2"));

        System.out.println("===========新增键值对并设置有效时间=============");
        System.out.println("添加元素设置过期时间：" + jedis.setex("key3", 10, "value3 -new"));
        System.out.println("获取：" + jedis.get("key3"));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("睡眠三秒： " + jedis.get("key3"));

        System.out.println("===========获取原值，更新为新值==========");
        System.out.println("获取key1更新： " + jedis.getSet("key1", "update new"));
        System.out.println("获取新值：" + jedis.get("key1"));

    }
}
