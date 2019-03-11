package com.springys.Common;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by yzd on 2019/3/5.
 */
public class RecvMQ {
    private final static String QUEUE_NAME = "Hello";

    public static void main(String[] args) throws  IOException,TimeoutException {
        Connection connection = getRabbitCon.getCon();
        Channel channel = connection.createChannel();
        //声明通道
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    }