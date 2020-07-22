package com.xsc.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author JakeXsc
 */
@SpringBootApplication
public class ZipKinHello02Application {

    public static void main(String[] args) {
        SpringApplication.run(ZipKinHello02Application.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
