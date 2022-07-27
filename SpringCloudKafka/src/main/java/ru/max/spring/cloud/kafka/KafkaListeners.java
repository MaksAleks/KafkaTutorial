package ru.max.spring.cloud.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Channels.class)
@RequiredArgsConstructor
public class KafkaListeners {

    @StreamListener(Channels.TEST)
    public void onMessage(String msg, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic) {
        if (msg.endsWith("error")) {
            throw new RuntimeException("Send to DQL: " + msg);
        }
        System.out.println(Thread.currentThread().getName() + ":\t" + topic + ":" + msg);
    }

    @StreamListener(Channels.TEST_ERROR)
    public void onError(String msg, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic) {
        System.out.println("ERROR: " + topic + ":" + msg);
    }
}
