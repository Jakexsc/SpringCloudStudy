package com.xsc.eurekaprovider.controller;

import commons.User;
import org.apache.commons.codec.net.URLCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/6/30 23:25
 */
@RestController
public class HelloController {
    @Value("${server.port}")
    Integer port;

    @GetMapping("/hello")
    public String hello() {
        return "hello xsc:" + port;
    }

    /**
     * 测试 RestTemplate Get
     *
     * @param name
     * @return String
     */
    @GetMapping("/hello2")
    public String hello2(String name) {
        System.out.println(new Date() + ": " + name);
        return "hello :" + name;
    }

    @PostMapping("addUser1")
    public User addUser1(User user) {
        return user;
    }

    @PostMapping("addUser2")
    public User addUser2(@RequestBody User user) {
        System.out.println(user);
        return user;
    }

    @PutMapping("updateUser1")
    public void updateUser1(User user) {
        System.out.println(user);
    }

    @PutMapping("updateUser2")
    public void updateUser2(@RequestBody User user) {
        System.out.println(user);
    }

    @DeleteMapping("/deleteUser1")
    public void deleteUser1(Integer id) {
        System.out.println(id);
    }

    @DeleteMapping("/deleteUser2/{id}")
    public void deleteUser2(@PathVariable Integer id) {
        System.out.println(id);
    }

    @GetMapping("/hello3")
    public void getUserByName(@RequestHeader String name) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode(name, "UTF-8"));
    }


}
