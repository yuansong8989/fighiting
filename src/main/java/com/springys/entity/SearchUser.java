package com.springys.entity;

import lombok.Data;

/**
 * Created by yzd on 2019/3/13.
 */
@Data
public class SearchUser {
    private  String term;
    private Integer desc;//等级降序
    private  Integer asc;//等级升序
    private Integer safeindex;//安全指数
    private Integer banlogin;//登陆权限
    private  int pageNum;
    private int pagesize;

}
