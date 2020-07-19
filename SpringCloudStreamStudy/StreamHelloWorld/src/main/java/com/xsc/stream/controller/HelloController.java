package com.xsc.stream.controller;

import com.xsc.stream.config.MyChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/19 22:34
 */
@RestController
public class HelloController {
    final MyChannel myChannel;

    public HelloController(MyChannel myChannel) {
        this.myChannel = myChannel;
    }

    @GetMapping("/hello")
    public void hello() {
        myChannel.output().send(MessageBuilder.withPayload("Hello SpringCloud Stream").build());
    }
}
