package com.xsc.consulconsumer.contoller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/5 1:01
 */
@RestController
public class HelloController {
    final LoadBalancerClient loadBalancerClient;
    final RestTemplate restTemplate;

    /**
     * 依赖注入 防止依赖为空
     * @param loadBalancerClient
     * @param restTemplate
     */
    public HelloController(LoadBalancerClient loadBalancerClient, RestTemplate restTemplate) {
        this.loadBalancerClient = loadBalancerClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hello")
    public void hello() {
        ServiceInstance choose = loadBalancerClient.choose("consul-provider");
        System.out.println("服务地址:" + choose.getUri());
        System.out.println("服务地址:" + choose.getServiceId());
        String forObject = restTemplate.getForObject(choose.getUri() + "/hello", String.class);
        System.out.println(forObject);
    }
}
