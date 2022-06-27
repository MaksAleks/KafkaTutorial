package ru.max.spring.cloud.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class StreamBridgeController {

    private String TOPIC_NAME = "test.clients-out-";

    private final StreamBridge streamBridge;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void newClient(@RequestBody Client client) {
        Message<Client> msg = MessageBuilder.withPayload(client)
                .setHeader(KafkaHeaders.MESSAGE_KEY, "TEST")
                .build();
        streamBridge.send(TOPIC_NAME + client.getName(), msg, MimeType.valueOf("application/json"));
    }
}
