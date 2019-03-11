package com.springys;

import com.springys.Service.implement.ServiceImplements;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by yzd on 2019/2/28.
 */
@Component
@Order(1)
@Slf4j
public class FristRun implements ApplicationRunner {
    @Resource
    private ServiceImplements serviceImplements;
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
    log.info("开始初始化程序");
    //判断数据库中在线情况 运行脚本
    serviceImplements.init();
    }
}
