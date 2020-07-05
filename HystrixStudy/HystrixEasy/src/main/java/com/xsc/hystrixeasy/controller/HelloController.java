package com.xsc.hystrixeasy.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.xsc.hystrixeasy.command.HelloCommand;
import com.xsc.hystrixeasy.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/5 14:59
 */
@RestController
public class HelloController {
    final HelloService helloService;
    final RestTemplate restTemplate;

    /**
     * 构造器注入 防止循环依赖
     *
     * @param helloService
     * @param restTemplate
     */
    @Autowired
    public HelloController(HelloService helloService, RestTemplate restTemplate) {
        this.helloService = helloService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hello")
    public String hello() {
        return this.helloService.hello();
    }

    /**
     * 继承HystrixCommand
     */
    @GetMapping("/hello2")
    public void hello2() {
        HelloCommand helloCommand = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("xsc")), restTemplate);
        //直接执行
        String execute = helloCommand.execute();
        System.out.println(execute);
        try {
            HelloCommand helloCommand2 = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("xsc")), restTemplate);
            //先入队 后执行
            Future<String> queue = helloCommand2.queue();
            String s = queue.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注解的方式异步调用
     *
     * @return String
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/hello3")
    public String hello3() throws ExecutionException, InterruptedException {
        Future<String> stringFuture = this.helloService.hello2();
        String s = stringFuture.get();
        return s;
    }
}
