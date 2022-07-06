package ru.max.spring.cloud.kafka;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Channels.class)
public class KafkaListeners {

    @StreamListener(Channels.TEST)
    public void onMessage(String msg) {
        System.out.println(Thread.currentThread().getName() + ":" + msg);
    }
}
