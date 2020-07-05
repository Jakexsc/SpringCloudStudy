package com.xsc.hystrixeasy.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import commons.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/6 0:14
 */
@Service
public class UserService {
    final RestTemplate restTemplate;

    /**
     * 构造器注入 防止循环依赖
     *
     * @param restTemplate http调用对象
     */
    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getUserById(List<Integer> ids) {
        User[] users = restTemplate.getForObject("http://eureka-provider/user/{1}", User[].class, StringUtils.join(ids, ","));
        return Arrays.asList(users);
    }

}
