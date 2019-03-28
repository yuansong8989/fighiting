package com.springys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor   //无参构造
@AllArgsConstructor   //有参构造
@Data   //get set方法
public class Test {
    private Integer id;
    private String name;
}
