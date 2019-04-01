package com.springys.entity;

import lombok.Data;

/**
 * Created by yzd on 2019/3/12.
 */
@Data
public class User {
    private int id;
    private String studentid;//学号 201510414424
    private String password;//密码限制 6-12位
    private String email;//qq邮箱 @qq.com
    private int grade;//无限制
    private int safeindex;//0-5
    private int banlogin; //1可登陆 0已禁止
    private String username;//昵称
    private int newsnum;//发布新闻数量
    private int discussnum;//发布评论数量
    private int seenum;//浏览新闻数量
    private String  lastlogintime;//上一次登陆时间
    private String  createtime;//加入系统时间
    private String role;
    private String authorjson;//自己关注
    private String byfollowjson;//被关注
}
