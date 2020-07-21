package com.xsc.sleuth.controller;

import com.xsc.sleuth.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/21 22:28
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    private final RestTemplate restTemplate;
    private final HelloService helloService;

    /**
     * @param restTemplate 调用对象
     * @param helloService
     */
    public HelloController(RestTemplate restTemplate, HelloService helloService) {
        this.restTemplate = restTemplate;
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello() {
        logger.info("hello spring cloud sleuth");
        return "hello spring cloud sleuth";
    }

    @GetMapping("/hello2")
    public String hello2() throws InterruptedException {
        logger.info("hello2");
        Thread.sleep(500);
        return restTemplate.getForObject("http://localhost:8080/hello3", String.class);
    }

    @GetMapping("/hello3")
    public String hello3() throws InterruptedException {
        logger.info("hello3");
        Thread.sleep(500);
        return "hello3";
    }

    /**
     * 异步任务是单独的spanId
     *
     * @return String
     */
    @GetMapping("/hello4")
    public String hello4() {
        logger.info("hello4");
        return this.helloService.backGroundFun();
    }
}
