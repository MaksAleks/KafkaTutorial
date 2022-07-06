package ru.max.spring.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MessageSenderController {

    private final StreamBridge streamBridge;

    @PostMapping(
            value = "/send/{topic}",
            consumes = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(@RequestBody String message, @PathVariable("topic") String topic) {
        streamBridge.send(topic, message);
    }
}
