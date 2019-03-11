package com.springys.Common;

import com.alibaba.fastjson.JSONObject;
import com.springys.entity.Students;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by yzd on 2019/2/28.
 */
@Component
public class QuartzManager {
    int i=0;
    public void addjob(Students students) throws SchedulerException {
    while (i<1){
            System.out.println("大家都南京南京");
            JobDataMap jobDataMap = new JobDataMap();
            String stringstudent = JSONObject.toJSONString(students);
            jobDataMap.put("student", stringstudent);
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//        //定义一个Trigger
//        Trigger trigger = newTrigger().withIdentity("trigger1", "group1") //定义name/group
//                .startNow()//一旦加入scheduler，立即生效
//                .withSchedule(simpleSchedule() //使用SimpleTrigger
//                        .withIntervalInSeconds(1) //每隔一秒执行一次
//                        .repeatForever()) //一直执行，奔腾到老不停歇
//                .build();
            JobDetail job = newJob()
                    .ofType(Quartzjob.class) //引用Job Class
                    .withIdentity("job", "group") //设置name/group
                    .setJobData(jobDataMap)
                    .build();
            SetOnlineStatusRequestModel.RunPlan runPlan = new SetOnlineStatusRequestModel.RunPlan();
            runPlan.setCronString("0 * * * * ? *");
            Trigger trigger = createTrigger("tname", "tgroup", 4, runPlan);
            scheduler.scheduleJob(job, trigger);
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
            i++;
//        scheduler.start();
//        try {
//
////            scheduler.shutdown(true);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        }
        System.out.println("运行结束");
    }

    private Trigger createTrigger(String triggerName, String triggerGroupName, Integer runPlanTpye, SetOnlineStatusRequestModel.RunPlan runPlan) {
        ScheduleBuilder scheduleBuilder = null;
        switch (runPlanTpye) {
            case StaticValues.RUN_PLAN_TYPE_EVERYTIME:
                if (runPlan.getEveryTime() < 0) {
                    throw new RuntimeException(MessageCode.error.getMessage());
                }
                switch (runPlan.getEveryTimeType()) {
                    case "month":
                        scheduleBuilder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInMonths(runPlan.getEveryTime());
                        break;
                    case "day":
                        scheduleBuilder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInDays(runPlan.getEveryTime());
                        break;
                    case "hour":
                        scheduleBuilder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInHours(runPlan.getEveryTime());
                        break;
                    case "minute":
                        scheduleBuilder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInMinutes(runPlan.getEveryTime());
                        break;
                    default:
                        throw new RuntimeException(MessageCode.error.getMessage());
                }

                break;
            case StaticValues.RUN_PLAN_TYPE_SOMETIME:
                scheduleBuilder = CronScheduleBuilder.dailyAtHourAndMinute(runPlan.getHour(), runPlan.getMinute());
                break;
            case StaticValues.RUN_PLAN_TYPE_DAYOFWEEK:
                scheduleBuilder = CronScheduleBuilder.weeklyOnDayAndHourAndMinute(runPlan.getDayOfWeek(), runPlan.getHour(), runPlan.getMinute());
                break;
            case StaticValues.RUN_PLAN_TYPE_DAYOFMONTH:
                scheduleBuilder = CronScheduleBuilder.monthlyOnDayAndHourAndMinute(runPlan.getDay(), runPlan.getHour(), runPlan.getMinute());
                break;
            case StaticValues.RUN_PLAN_TYPE_CONSUME:
                scheduleBuilder = CronScheduleBuilder.cronSchedule(runPlan.getCronString());
                break;
            default:
                throw new RuntimeException(MessageCode.error.getMessage());
        }
        return TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
                .withSchedule(scheduleBuilder)
                .build();
    }

}
