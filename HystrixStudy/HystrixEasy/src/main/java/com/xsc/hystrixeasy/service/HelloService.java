package com.xsc.hystrixeasy.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/5 14:57
 */
@Service
public class HelloService {
    final RestTemplate restTemplate;

    /**
     * 构造器注入 防止循环依赖
     *
     * @param restTemplate
     */
    public HelloService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 调用provider的hello接口 可能会失败 添加注解 HystrixCommand
     *
     * @return String
     */
    @HystrixCommand(fallbackMethod = "error", ignoreExceptions = ArithmeticException.class)
    public String hello() {
        int i = 1 / 0;
        return restTemplate.getForObject("http://eureka-provider/hello", String.class);
    }

    /**
     * 该方法名字和 fallbackMethod = "error"一致
     * Consumer异常 注解方式
     *
     * @return String
     */
    public String error(Throwable t) {
        return "error :" + t.getMessage();
    }

    /**
     * 通过注解实现异步调用
     *
     * @return Future<String>
     */
    @HystrixCommand(fallbackMethod = "error")
    public Future<String> hello2() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://eureka-provider/hello", String.class);
            }
        };
    }

    /**
     * 增加 @CacheResult 表示该结果会被缓存 缓存的key就是方法的参数， value是该方法的返回值
     * 需初始化HystrixRequestContext
     *
     * @param name
     * @return String
     */
    @HystrixCommand(fallbackMethod = "error2")
    @CacheResult
    public String hello3(String name) {
        return restTemplate.getForObject("http://eureka-provider/hello2?name={1}", String.class, name);
    }

    /**
     * 如果有多个参数 缓存的key则是{name} + {age}
     * 如果加了@CacheKey 则表示缓存的key以{name}为准
     *
     * @param name
     * @return String
     */
    @HystrixCommand(fallbackMethod = "error2")
    @CacheResult
    public String hello4(@CacheKey String name, Integer age) {
        return restTemplate.getForObject("http://eureka-provider/hello2?name={1}", String.class, name, age);
    }

    /**
     * 增加@CacheRemove则删除缓存 commandKey是增加缓存的方法名称
     *
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "error2")
    @CacheRemove(commandKey = "hello3")
    public String deleteCacheByName(@CacheKey String name) {
        return "删除缓存 key :" + name;
    }

    public String error2(String name) {
        return "error2 :" + name;
    }
}
