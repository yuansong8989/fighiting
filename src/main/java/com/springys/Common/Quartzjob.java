package com.springys.Common;

import com.alibaba.fastjson.JSONObject;
import com.springys.entity.Students;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by yzd on 2019/2/28.
 */
@DisallowConcurrentExecution//保证多个任务间不会同时执行.所以在多任务执行时最好加上
@Slf4j

public class Quartzjob implements Job {
    //运行脚本文件

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
         JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();//获取jobdatamap
        String s = jobDataMap.getString("student");
        Students students = JSONObject.parseObject(s, Students.class);
//        log.info("获取数据1：" + students.getFile_url());
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.redirectErrorStream(true);
        processBuilder.command("E:\\kettle\\data-integration\\kitchen.bat","  -file ","G:\\Windows\\JFS\\SCRIPT\\1\\zbl.kjb");
        String os=System.getProperty("os.name");
        boolean isWindows=false;
        String tm1=null;
        String tm2=null;
        if(os.startsWith("win")){
            isWindows=true;
        }
        try {
            processBuilder.start();
            BufferedReader    readStdout = new BufferedReader(new InputStreamReader(processBuilder.start().getInputStream(), isWindows ? Charset.forName("GBK") : Charset.forName("UTF-8")));
            BufferedReader  readStderr = new BufferedReader(new InputStreamReader(processBuilder.start().getErrorStream(), isWindows ? Charset.forName("GBK") : Charset.forName("UTF-8")));
            while ((tm1 = readStdout.readLine()) != null || (tm2 = readStderr.readLine()) != null){
                System.out.println("==>STDOUT  >"+tm1);
                System.out.println("==>STDOUT  >"+tm2);

            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
