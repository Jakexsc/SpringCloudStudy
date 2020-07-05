package com.xsc.hystrixeasy.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
     * @return String
     */
    @HystrixCommand(fallbackMethod = "error")
    public String hello() {
        return restTemplate.getForObject("http://eureka-provider/hello", String.class);
    }

    /**
     * 该方法名字和 fallbackMethod = "error"一致
     * @return String
     */
    public String error() {
        return "error";
    }
}
