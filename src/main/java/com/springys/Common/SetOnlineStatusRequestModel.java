package com.springys.Common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class SetOnlineStatusRequestModel {
    private Data data;

    @lombok.Data
    public static class Data {
        private Integer id;
        private Integer runTimesTotal;//运行总次数
        private Integer runPlanTpye;//0每隔一定时间 1每天某个时间 2每周某个时间 3每月某个时间 4用户自定义
        private RunPlan runPlan;//运行参数
        private Integer status;//状态，0未上线，1上线

    }

    /*
    请求参数说明

    * 0每隔一定时间 everyTime everyTimeType  //1 minute
    * 1每天某个时间 hour minute  //10 50
    * 2每周某个时间 dayOfWeek hour minute // 2 10 50
    * 3每月某个时间 day hour minute  // 10 10 50
    * 4用户自定义  cronString  //0/30 * * * * ?
     * */
    @lombok.Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RunPlan {
        private Integer everyTime;// 每隔一定时间
        private String everyTimeType;// minute 分 hour 小时 day 天 month 月
        private Integer minute;
        private Integer hour;
        private Integer day;
        private Integer dayOfWeek;//1234567
        private String cronString;//

    }
}
