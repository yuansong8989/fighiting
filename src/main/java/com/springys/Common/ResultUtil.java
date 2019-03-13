package com.springys.Common;

public class ResultUtil {

    public static ResultModel success() {
        ResultModel resultModel = new ResultModel(RequestResultEnum.SUCCESS);
        resultModel.setResult(true);
        return resultModel;
    }

    public static <T> ResultModel success(T t) {
        ResultModel resultModel = new ResultModel(RequestResultEnum.SUCCESS);
        resultModel.setResult(true);
        resultModel.setData(t);
        return resultModel;
    }

    public static ResultModel error(RequestResultEnum requestResultEnum) {
        return new ResultModel(requestResultEnum);
    }
    public static ResultModel operation_error() {
        return new ResultModel(RequestResultEnum.operation_error);
    }
    public static ResultModel error() {
        ResultModel resultModel = new ResultModel(RequestResultEnum.FAIL_PARAM_ERROR);
        resultModel.setResult(false);
        return resultModel;
    }

    public static ResultModel error(RequestResultEnum requestResultEnum, String msg) {
        return new ResultModel(requestResultEnum, msg);
    }
    public static ResultModel error(int code, String msg) {
        ResultModel resultModel = new ResultModel(RequestResultEnum.SUCCESS);
        resultModel.setResult(false);
        resultModel.setCode(code);
        resultModel.setMessage(msg);
        return resultModel;
    }
}
