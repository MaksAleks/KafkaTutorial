package ru.max.spring.cloud.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface Channels {

    String TEST = "test";

    @Input(TEST)
    MessageChannel testChannel();
}