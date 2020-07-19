package com.xsc.stream.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * EnableBinding表示绑定sink消息通道
 *
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/19 22:03
 */
@EnableBinding(Sink.class)
public class MsgReceiver {
    private static final Logger logger = LoggerFactory.getLogger(MsgReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receiver(Object payload) {
        logger.info("Receiver:" + payload);
    }
}
