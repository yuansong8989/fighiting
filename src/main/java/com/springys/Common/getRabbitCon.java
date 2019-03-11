package com.springys.Common;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by yzd on 2019/3/5.
 */
public class getRabbitCon {
    public static Connection getCon() throws IOException,TimeoutException{
        ConnectionFactory connectionFactory= new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = connectionFactory.newConnection();
        return connection;
    }
}
