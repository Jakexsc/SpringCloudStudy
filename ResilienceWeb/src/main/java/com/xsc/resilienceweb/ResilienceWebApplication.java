package com.xsc.resilienceweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author JakeXsc
 */
@SpringBootApplication
public class ResilienceWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResilienceWebApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
