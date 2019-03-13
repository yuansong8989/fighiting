package com.springys.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by yzd on 2019/2/21.
 */
@Data
public class FilePage {
    private int total;//查出的文件/用户总数
    private List<String>list;
    private List<User> list1;
}
