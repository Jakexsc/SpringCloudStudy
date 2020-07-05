package com.xsc.hystrixeasy.command;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/5 20:59
 */
public class HelloCommand extends HystrixCommand<String> {
    final RestTemplate restTemplate;
    final String name;

    /**
     * 构造器注入 防止循环依赖
     *
     * @param setter
     * @param restTemplate HTTP调用对象
     * @param name         缓存key
     */
    @Autowired
    public HelloCommand(Setter setter, RestTemplate restTemplate, String name) {
        super(setter);
        this.restTemplate = restTemplate;
        this.name = name;
    }

    @Override
    public String run() throws Exception {
        return restTemplate.getForObject("http://eureka-provider/hello2?name={1}", String.class, name);
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

    /**
     * 继承方式缓存 重写getCacheKey
     * 得到缓存key
     *
     * @return String 缓存的key
     */
    @Override
    protected String getCacheKey() {
        return name;
    }
}
