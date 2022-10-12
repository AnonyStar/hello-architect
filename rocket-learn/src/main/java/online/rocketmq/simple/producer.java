package online.rocketmq.simple;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author: zhoucx
 * @time: 2021-12-03
 */
public class producer {


    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException {

        DefaultMQProducer producer = new DefaultMQProducer("Group-1");
        producer.setNamesrvAddr("192.168.56.110:9876");
        producer.start();

        for (int i = 0; i < 100 ; i++) {
            try {
                Message msg = new Message("Topic-test-1","tagA", "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));

                final SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (MQBrokerException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
