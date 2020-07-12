package com.xsc.resilienceweb.controller;

import com.xsc.resilienceweb.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/13 1:21
 */
@RestController
public class HelloController {
    final HelloService helloService;

    /**
     * 构造器注入
     * @param helloService
     */
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello() {
        return this.helloService.hello();
    }
}
