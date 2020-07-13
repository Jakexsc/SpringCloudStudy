package com.xsc.hystrixeasy.controller;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.xsc.hystrixeasy.command.UserCollapseCommand;
import com.xsc.hystrixeasy.service.UserService;
import commons.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/6 0:42
 */
@RestController
public class UserController {
    final UserService userService;

    /**
     * 构造器注入 防止依赖为空
     *
     * @param userService
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 使用继承的方式请求合并
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/user")
    public void user() throws ExecutionException, InterruptedException {
        HystrixRequestContext hrc = HystrixRequestContext.initializeContext();
        UserCollapseCommand ucc1 = new UserCollapseCommand(99, userService);
        UserCollapseCommand ucc2 = new UserCollapseCommand(98, userService);
        UserCollapseCommand ucc3 = new UserCollapseCommand(97, userService);
        Future<User> queue1 = ucc1.queue();
        Future<User> queue2 = ucc2.queue();
        Future<User> queue3 = ucc3.queue();
        User user1 = queue1.get();
        User user2 = queue2.get();
        User user3 = queue3.get();
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        UserCollapseCommand ucc4 = new UserCollapseCommand(96, userService);
        Future<User> queue4 = ucc4.queue();
        User user4 = queue4.get();
        System.out.println(user4);
        hrc.close();
    }

    /**
     * 使用注解的方式请求合并
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/user2")
    public void user2() throws ExecutionException, InterruptedException {
        HystrixRequestContext hrc = HystrixRequestContext.initializeContext();
        Future<User> q1 = this.userService.annotationHystrixCollapser(99);
        Future<User> q2 = this.userService.annotationHystrixCollapser(98);
        Future<User> q3 = this.userService.annotationHystrixCollapser(97);
        User user1 = q1.get();
        User user2 = q2.get();
        User user3 = q3.get();
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        Thread.sleep(2000);
        Future<User> q4 = this.userService.annotationHystrixCollapser(96);
        User user4 = q4.get();
        System.out.println(user4);
        hrc.close();
    }
}
