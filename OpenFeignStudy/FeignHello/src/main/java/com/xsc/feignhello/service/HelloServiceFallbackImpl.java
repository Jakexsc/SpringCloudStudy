package com.xsc.feignhello.service;

import commons.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

/**
 * 注意加上RequestMapping 防止请求地址冲突
 *
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/7 0:02
 */
@Component
@RequestMapping("/xsc")
public class HelloServiceFallbackImpl implements HelloService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello2(String name) {
        return "error";

    }

    @Override
    public void addUser2(User user) {

    }

    @Override
    public void deleteUser2(Integer id) {

    }

    @Override
    public void hello3(String name) throws UnsupportedEncodingException {

    }
}
