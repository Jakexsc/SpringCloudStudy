package com.xsc.stream.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Date;

/**
 * @author JakeXsc
 * @version 1.0
 * @date 2020/7/19 22:32
 */
@EnableBinding(MyChannel.class)
public class MsgReceiverCustom {
    private static final Logger logger = LoggerFactory.getLogger(MsgReceiver.class);

    @StreamListener(MyChannel.INPUT)
    public void receiver(Object payload) {
        logger.info("Receiver:" + payload + ":" + new Date());
    }
}
