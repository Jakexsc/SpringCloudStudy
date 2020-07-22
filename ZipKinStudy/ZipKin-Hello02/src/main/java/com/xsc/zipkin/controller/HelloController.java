package com.xsc.zipkin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/22 22:18
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public void hello() {
        logger.info("zipkin-hello02");
        this.restTemplate.getForObject("http://localhost:8080/hello?{name}", String.class, "xsc");
    }
}
