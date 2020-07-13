package com.xsc.resilienceweb.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/13 1:15
 */
@Service
@CircuitBreaker(name = "cbA", fallbackMethod = "error")
@Retry(name = "retryA")
public class HelloService {
    final RestTemplate restTemplate;

    /**
     * 构造器注入
     *
     * @param restTemplate 远程调用工具
     */
    public HelloService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String hello() {
        return this.restTemplate.getForObject("http://localhost:1113/hello", String.class);
    }
    public String error(Throwable throwable) {
        return "error";
    }
}
