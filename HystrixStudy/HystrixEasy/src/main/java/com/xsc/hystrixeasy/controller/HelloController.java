package com.xsc.hystrixeasy.controller;

import com.xsc.hystrixeasy.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/5 14:59
 */
@RestController
public class HelloController {
    final HelloService helloService;

    /**
     * 构造器注入 防止循环依赖
     *
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
