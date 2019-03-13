package com.springys.exception;

import com.springys.Common.RequestResultEnum;

/**
 * @author zyh32
 */
public class ComputeException extends RuntimeException {

    public ComputeException(RequestResultEnum requestResultEnum) {
        super(requestResultEnum.getMsg());
        this.code = requestResultEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;
}
