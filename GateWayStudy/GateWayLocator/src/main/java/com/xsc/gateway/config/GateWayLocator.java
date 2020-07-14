package com.xsc.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/14 23:56
 */
@Configuration
public class GateWayLocator {
    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("xsc-a", (PredicateSpec p) -> p.path("/get").uri("http://httpbin.org"))
                .build();
    }
}
