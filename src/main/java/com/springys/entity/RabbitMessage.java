package com.springys.entity;

import lombok.Data;

/**
 * Created by yzd on 2019/4/4.
 */
@Data
public class RabbitMessage {
    private String message;
    private String  sendstid;
    private String acceptstid;
}
