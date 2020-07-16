package com.xsc.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/15 22:47
 */
@RestController
@RefreshScope
public class HelloController {
    @Value("${xsc}")
    String xsc;
    @GetMapping("/hello")
    public String hello() {
        return xsc;
    }
}
