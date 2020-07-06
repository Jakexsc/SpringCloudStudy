package com.xsc.feignhello.contoller;

import com.xsc.feignhello.service.HelloService;
import commons.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/6 22:13
 */
@RestController
public class HelloController {
    final HelloService helloService;

    /**
     * 构造器注入 防止循环依赖
     *
     * @param helloService openfeign逻辑层
     */
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello() {
        return this.helloService.hello();
    }

    @GetMapping("/hello2")
    public void hello2() {
        this.helloService.hello2("xsc");
    }

    @GetMapping("/addUser")
    public void addUser() {
        User user = new User();
        user.setId(1);
        user.setPassword("chen-2964");
        user.setName("xsc");
        this.helloService.addUser2(user);
    }

    @GetMapping("/deleteUser")
    public void deleteUser() {
        this.helloService.deleteUser2(1);
    }

    @GetMapping("/selectHeader")
    public void selectHeader() throws UnsupportedEncodingException {
        this.helloService.hello3(URLEncoder.encode("谢树琛", "UTF-8"));
    }
}
