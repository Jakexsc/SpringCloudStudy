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
        int i = 1 / 0;
        return restTemplate.getForObject("http://eureka-provider/hello", String.class);
    }

    /**
     * 继承容错
     * Consumer异常 继承方式
     *
     * @return String
     */
    @Override
    protected String getFallbackMethodName() {
        return "error :" + getExecutionException().getMessage();
    }
}
