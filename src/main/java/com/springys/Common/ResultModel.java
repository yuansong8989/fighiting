package com.springys.Common;

import lombok.Data;

@Data
public class ResultModel<T> {

    private String message;
    private boolean result;
    private int code;
    private T data;



    public ResultModel(RequestResultEnum requestResultEnum) {
        this.code = requestResultEnum.getCode();
        this.message = requestResultEnum.getMsg();
    }

    public ResultModel() {
    }

    public ResultModel(RequestResultEnum requestResultEnum, String msg) {
        this.code = requestResultEnum.getCode();
        this.message = requestResultEnum.getMsg() + " " + msg;
    }
}
