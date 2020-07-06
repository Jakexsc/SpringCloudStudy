package com.xsc.feignhello;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author JakeXsc
 */
@SpringBootApplication
@EnableFeignClients
public class FeignHelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignHelloApplication.class, args);
    }

    /**
     * 开启Feign日志打印
     *
     * @return
     */
    @Bean
    Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

}
