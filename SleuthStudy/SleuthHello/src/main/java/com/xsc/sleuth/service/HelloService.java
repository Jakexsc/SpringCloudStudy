package com.xsc.sleuth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/21 22:44
 */
@Service
public class HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    @Async
    public String backGroundFun() {
        logger.info("backGroundFun");
        return "backGroundFun";
    }

    /**
     * 定时任务每次都会产生新的traceId SpanId每次调用都一样
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduledTest() {
        logger.info("start");
        this.backGroundFun();
        logger.info("end");
    }

}
