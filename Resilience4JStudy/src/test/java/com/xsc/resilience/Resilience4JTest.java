package com.xsc.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/7 23:21
 */

public class Resilience4JTest {
    @Test
    public void test1() {
        //获取一个 CircuitBreakerRegistry 实例 用ofDefaults或自定义
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                //故障阈值百分比, 超过这个阈值, 断路器就会打开
                .failureRateThreshold(50)
                // 熔断器保持打开的时间 熔断器就会进入 half open
                .waitDurationInOpenState(Duration.ofMillis(1000))
                // 当熔断器状态是half open状态时， 环形缓冲区的大小
                .ringBufferSizeInHalfOpenState(2)
                .ringBufferSizeInClosedState(2)
                .build();
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("xsc");
        CheckedFunction0<String> supplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "hello resilience4j");
        Try<String> result = Try.of(supplier)
                .map(v -> v + "hello world");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    @Test
    public void test2() {
        //获取一个 CircuitBreakerRegistry 实例 用ofDefaults或自定义
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                //故障阈值百分比, 超过这个阈值, 断路器就会打开
                .failureRateThreshold(50)
                // 熔断器保持打开的时间 熔断器就会进入 half open
                .waitDurationInOpenState(Duration.ofMillis(1000))
                // 当熔断器状态是half open状态时， 环形缓冲区的大小
                .ringBufferSizeInClosedState(2)
                .build();
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("xsc");
        System.out.println(circuitBreaker.getState());
        circuitBreaker.onError(0, TimeUnit.NANOSECONDS, new RuntimeException());
        System.out.println(circuitBreaker.getState());
        circuitBreaker.onError(0, TimeUnit.NANOSECONDS, new RuntimeException());
        System.out.println(circuitBreaker.getState());
        CheckedFunction0<String> supplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "hello resilience4j");
        Try<String> result = Try.of(supplier)
                .map(v -> v + "hello world");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    @Test
    public void test3() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                // 阈值刷新的时间
                .limitRefreshPeriod(Duration.ofMillis(1000))
                // 阈值刷新的频次(每秒处理几个请求)
                .limitForPeriod(4)
                // 限流之后的冷却时间
                .timeoutDuration(Duration.ofMillis(1000))
                .build();
        RateLimiter rateLimiter = RateLimiter.of("xsc", config);
        CheckedRunnable checkedRunnable = RateLimiter.decorateCheckedRunnable(rateLimiter, () -> {
            System.out.println(new Date());
        });
        Try.run(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable)
                .andThenTry(checkedRunnable)
                .onFailure((Throwable t) -> System.out.println(t.getMessage()));
    }
}
