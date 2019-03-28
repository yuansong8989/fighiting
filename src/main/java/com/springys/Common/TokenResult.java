package com.springys.Common;

import lombok.Data;

/**
 * Created by yzd on 2019/3/26.
 */
@Data
public class TokenResult {
    private  String name;
    private String avatar;
    private String roles[];
    private String introduction;
}
