package com.xsc.stream.controller;

import com.xsc.stream.config.MyChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/19 22:34
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    final MyChannel myChannel;

    public HelloController(MyChannel myChannel) {
        this.myChannel = myChannel;
    }

    @GetMapping("/hello")
    public void hello() {
        logger.info("send msg:" + new Date());
        myChannel.output().send(MessageBuilder.withPayload("Hello SpringCloud Stream").setHeader("x-delay", 3000).build());
    }
}
