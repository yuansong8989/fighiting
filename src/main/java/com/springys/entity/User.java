package com.springys.entity;

import lombok.Data;

/**
 * Created by yzd on 2019/3/12.
 */
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private int grade;
    private int safeindex;
}
