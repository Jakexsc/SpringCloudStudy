package com.xsc.nacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/22 23:57
 */
@RestController
public class HelloController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() {
        return this.restTemplate.getForObject("http://nacos01/hello", String.class);
    }
}
