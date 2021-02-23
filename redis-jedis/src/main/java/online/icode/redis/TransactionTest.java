package online.icode.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author: zhoucx
 * @time: 2021/2/22 16:24
 */
public class TransactionTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.56.10", 6379);
        jedis.flushDB();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("name", "java");
        //开启事务
        Transaction multi = jedis.multi();
        String result = jsonObject.toJSONString();
        try {
            //向redis存入一条数据
            multi.set("json", result);
            //再存入一条数据
            multi.set("json2", result);
            //这里引发了异常，用0作为被除数
            int i = 100 / 0;
            //如果没有引发异常，执行进入队列的命令
            multi.exec();
        } catch (Exception e) {
            e.printStackTrace();
            //如果出现异常，回滚
            multi.discard();
        } finally {
            System.out.println(jedis.get("json"));
            System.out.println(jedis.get("json2"));
            //最终关闭客户端
            jedis.close();
        }
    }
}
