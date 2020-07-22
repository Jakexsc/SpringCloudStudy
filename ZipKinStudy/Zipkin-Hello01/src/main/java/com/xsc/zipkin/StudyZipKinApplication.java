package com.xsc.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jakexsc
 */
@SpringBootApplication
public class StudyZipKinApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyZipKinApplication.class, args);
    }
}
