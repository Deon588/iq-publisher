package com.deon.iqpublisher.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.IllegalArgumentException;

/**
 *Service used to send messages to rabbitMQ node
 */
public class PublishService {
    private static final Logger logger = LoggerFactory.getLogger(PublishService.class);

    private ConnectionFactory cf;
    private String host;

    /**
     *
     * @param host the host where the rabbitMQ node is running.
     */
    public PublishService(String host) {
        if (host.isEmpty()) throw new IllegalArgumentException("Host cannot be empty");
        this.host = host;
        this.cf = new ConnectionFactory();
        this.cf.setHost(this.host);
    }

    /**
     *
     * @param message the message to send to the rabbitMQ node
     * @param queue the name of the queue where the above message will be queued
     *
     */
    public void send(String message, String queue) {
        if (message.isEmpty() || queue.isEmpty()) throw new IllegalArgumentException("Neither message nor queue can be empty");

        try (Connection conn= this.cf.newConnection();
             Channel channel = conn.createChannel()) {
            channel.queueDeclare(queue, false, false, false, null);
            channel.basicPublish("", queue, null, message.getBytes());
            System.out.println("Message sent");
        } catch (Exception e) {
            logger.error("Error", e);
        }
    }

    public String getHost() {
        return  this.host;
    }


}

