package com.xsc.api;

import commons.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/6 23:06
 */
public interface IUserApi {
    /**
     * 调用provider的方法
     *
     * @return String
     */
    @GetMapping("/hello")
    String hello();

    /**
     * 调用provider的查询方法
     *
     * @param name
     * @return String
     */
    @GetMapping("/hello2")
    String hello2(@RequestParam("name") String name);

    /**
     * 调用provider的添加方法
     *
     * @param user
     * @return User
     */
    @PostMapping("/addUser2")
    void addUser2(@RequestBody User user);

    /**
     * 调用provider的删除方法
     *
     * @param id
     */
    @DeleteMapping("/deleteUser2/{id}")
    void deleteUser2(@PathVariable(value = "id") Integer id);

    /**
     * 调用provider的头部传参方法
     *
     * @param name
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/hello3")
    void hello3(@RequestHeader("name") String name) throws UnsupportedEncodingException;
}
