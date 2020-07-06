package com.xsc.feignhello.service;

import commons.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/6 22:10
 */
@FeignClient("eureka-provider")
public interface HelloService {
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
    void deleteUser2(@PathVariable Integer id);

    /**
     * 调用provider的头部传参方法
     *
     * @param name
     */
    @GetMapping("/hello3")
    void hello3(@RequestHeader("name") String name);
}
