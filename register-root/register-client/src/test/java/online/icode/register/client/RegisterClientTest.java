package online.icode.register.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterClientTest {

    @Test
    void start() {
        RegisterClient client = new RegisterClient();
        client.start();
    }

    public static void main(String[] args) {
        RegisterClient client = new RegisterClient();
        client.start();
    }
}