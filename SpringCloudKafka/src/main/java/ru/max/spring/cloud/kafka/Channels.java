package ru.max.spring.cloud.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface Channels {

    String TEST = "test";

    String TEST_ERROR = "test-error";

    @Input(TEST)
    MessageChannel testChannel();

    @Input(TEST_ERROR)
    MessageChannel testErrorChannel();
}