package com.xsc.feignhello.service;

import com.xsc.api.IUserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/6 22:10
 */
@FeignClient("eureka-provider")
public interface HelloService extends IUserApi {
}
