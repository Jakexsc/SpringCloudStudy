package com.xsc.hystrixeasy.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
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
     * 构造器注入 防止依赖为空
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
        // 开启缓存
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        HelloCommand helloCommand = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("xsc")), restTemplate, "xsc");
        //直接执行
        String execute = helloCommand.execute();
        System.out.println(execute);
        try {
            HelloCommand helloCommand2 = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("xsc")), restTemplate, "xsc");
            //先入队 后执行
            Future<String> queue = helloCommand2.queue();
            String s = queue.get();
            System.out.println(s);
            // 缓存关闭
            hystrixRequestContext.close();
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

    @GetMapping("/hello4")
    public void hello4() {
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
        String xsc = this.helloService.hello3("xsc");
        xsc = this.helloService.hello3("xsc");
        ctx.close();
    }

    @GetMapping("/hello5")
    public void hello5() {
        //缓存开始
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
        String xsc = this.helloService.hello3("xsc");
        //删除缓存
        this.helloService.deleteCacheByName("xsc");
        xsc = this.helloService.hello3("xsc");
        //缓存结束
        ctx.close();
    }
}
