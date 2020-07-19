package com.xsc.stream.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/19 22:28
 */
public interface MyChannel {
    String INPUT = "xsc-input";
    String OUTPUT = "xsc-output";

    /**
     * 消息生产者
     *
     * @return MessageChannel
     */
    @Output(OUTPUT)
    MessageChannel output();

    /**
     * 消息消费者
     *
     * @return SubscribableChannel
     */
    @Input(INPUT)
    SubscribableChannel input();
}
