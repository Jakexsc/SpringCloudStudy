package com.xsc.feignhello.service;

import com.xsc.api.IUserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;

/**
 * 两种方式 一种是定义一个HelloServiceFallbackImpl 然后增加属性 fallback = HelloServiceFallbackImpl.class
 * 一种是fallbackFactory 然后实现FallFactory的接口 增加属性 fallbackFactory = HelloServiceFallbackFactoryImpl.class
 *
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/6 22:10
 */
@FeignClient(value = "eureka-provider", fallbackFactory = HelloServiceFallbackFactoryImpl.class)
@Repository
public interface HelloService extends IUserApi {
}
