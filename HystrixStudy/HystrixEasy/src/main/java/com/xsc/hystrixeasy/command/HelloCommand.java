package com.xsc.hystrixeasy.command;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/5 20:59
 */
public class HelloCommand extends HystrixCommand<String> {
    final RestTemplate restTemplate;

    /**
     * 构造器注入 防止循环依赖
     *
     * @param setter
     * @param restTemplate
     */
    public HelloCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    @Override
    public String run() throws Exception {
        return restTemplate.getForObject("http://eureka-provider/hello", String.class);
    }
}