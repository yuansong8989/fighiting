package com.springys.Common;

public class StaticValues {

    public static final int STATUS_NOT_ONLINE = 0;
    public static final int STATUS_ONLINE = 1;
    public static final String STATUS_ONLINE_STRING = "在线";
    public static final String STATUS_OFFLINE_STRING = "离线";


    public static final int RUN_STATE_RUNING = 1;
    public static final int RUN_STATE_OK = 2;
    public static final int RUN_STATE_FAIL = 3;

    //执行计划类型 0每隔一定时间 1每天某个时间 2每周某个时间 3每月某个时间 4用户自定义
    public static final int RUN_PLAN_TYPE_EVERYTIME = 0;
    public static final int RUN_PLAN_TYPE_SOMETIME = 1;
    public static final int RUN_PLAN_TYPE_DAYOFWEEK = 2;
    public static final int RUN_PLAN_TYPE_DAYOFMONTH = 3;
    public static final int RUN_PLAN_TYPE_CONSUME = 4;
}
