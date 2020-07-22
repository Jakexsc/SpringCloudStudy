package com.xsc.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/22 23:37
 */
@RestController
@RefreshScope
public class HelloController {
    @Value("${name}")
    String name;

    @GetMapping("/hello")
    public String hello() {
        return name;
    }
}
