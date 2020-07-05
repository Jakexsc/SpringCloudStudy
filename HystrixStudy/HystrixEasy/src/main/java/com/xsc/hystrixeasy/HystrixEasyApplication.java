package com.xsc.hystrixeasy;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author JakeXsc
 *
 * @SpringBootApplication
 * @EnableDiscoveryClient
 * @EnableCircuitBreaker
 */
@SpringCloudApplication
public class HystrixEasyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixEasyApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
