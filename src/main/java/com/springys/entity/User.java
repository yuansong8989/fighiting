package com.springys.entity;

import lombok.Data;

/**
 * Created by yzd on 2019/3/12.
 */
@Data
public class User {
    private int id;
    private String username;//学号 201510414424
    private String password;//密码限制 6-12位
    private String email;//qq邮箱 @qq.com
    private int grade;//无限制
    private int safeindex;//0-5
    private int banlogin; //可登陆 已禁止
}
