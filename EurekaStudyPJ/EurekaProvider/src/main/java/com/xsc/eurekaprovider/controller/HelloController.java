package com.xsc.eurekaprovider.controller;

import com.xsc.api.IUserApi;
import commons.User;
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
public class HelloController implements IUserApi {
    @Value("${server.port}")
    Integer port;

    @Override
    public String hello() {
        System.out.println("hello xsc:" + port);
        int i = 1 / 0;
        return "hello xsc:" + port;
    }

    /**
     * 测试 RestTemplate Get
     *
     * @param name
     * @return String
     */
    @Override
    public String hello2(String name) {
        System.out.println(new Date() + ": " + name);
        return "hello :" + name;
    }

    @PostMapping("addUser1")
    public User addUser1(User user) {
        return user;
    }

    @Override
    public void addUser2(@RequestBody User user) {
        System.out.println(user);
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

    @Override
    public void deleteUser2(@PathVariable Integer id) {
        System.out.println(id);
    }

    @Override
    public void hello3(@RequestHeader String name) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode(name, "UTF-8"));
    }


}
