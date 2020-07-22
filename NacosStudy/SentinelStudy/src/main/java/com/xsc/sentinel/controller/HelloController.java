package com.xsc.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/23 0:11
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello sentinel";
    }
}
