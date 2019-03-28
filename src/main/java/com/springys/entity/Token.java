package com.springys.entity;

import lombok.Data;

/**
 * Created by yzd on 2019/3/26.
 */
@Data
public class Token {
    private String token;
    private String username;
    private String password;
}
