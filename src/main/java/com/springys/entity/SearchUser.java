package com.springys.entity;

import lombok.Data;

/**
 * Created by yzd on 2019/3/13.
 */
@Data
public class SearchUser {
    private  String term;
    private int desc;//等级降序
    private  int asc;//等级升序
    private int safeindex;//安全指数

}
