package online.icode.register.client;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class RegisterClientTest {

    @Test
    void start() throws InterruptedException {
        RegisterClient client = new RegisterClient();
        client.start();

        TimeUnit.SECONDS.sleep(30);

        client.shutdown();
    }

    public static void main(String[] args) {
        RegisterClient client = new RegisterClient();
        client.start();
    }
}