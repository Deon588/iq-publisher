package com.deon.iqpublisher;

import com.deon.iqpublisher.service.PublishService;
import com.github.fridujo.rabbitmq.mock.MockConnectionFactory;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPublishService {

    //private static final String QUEUE_NAME = "a";

    //private ConnectionFactory mf = new MockConnectionFactory();


    @Test
    void EmptyHostParamConstr() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            PublishService publishService = new PublishService("");
        });
        assertEquals("Host cannot be empty", e.getMessage());
    }

    @Test
    void successfulConstr() {
        PublishService publishService = new PublishService("localhost");

        assertEquals("localhost", publishService.getHost());
    }

    @Test
    void EmptyStringParamSend() {
        PublishService publishService = new PublishService("localhost");

        Exception e = assertThrows(IllegalArgumentException.class, () -> publishService.send("", "a"));
        assertEquals("Neither message nor queue can be empty", e.getMessage());
    }

}
