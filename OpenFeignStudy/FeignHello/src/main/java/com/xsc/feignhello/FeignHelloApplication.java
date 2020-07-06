package com.xsc.feignhello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author JakeXsc
 */
@SpringBootApplication
@EnableFeignClients
public class FeignHelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignHelloApplication.class, args);
    }

}
