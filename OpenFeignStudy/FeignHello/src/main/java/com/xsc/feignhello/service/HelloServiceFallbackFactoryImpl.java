package com.xsc.feignhello.service;

import commons.User;
import feign.hystrix.FallbackFactory;

import java.io.UnsupportedEncodingException;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/7 0:10
 */
public class HelloServiceFallbackFactoryImpl implements FallbackFactory<HelloService> {
    @Override
    public HelloService create(Throwable throwable) {
        return new HelloService() {
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
        };
    }
}
