package ru.max.kafka;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka/input")
@RequiredArgsConstructor
public class InputMessagesController {

    private final KafkaProducer<String, Message> kafkaProducer;

    @PostMapping(
            value = "/{destination}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void message(@RequestBody Message msg, @PathVariable("destination") String destination) {
        ProducerRecord<String, Message> record = new ProducerRecord<>(destination, msg);
        kafkaProducer.send(record, (metadata, exception) -> {
            if (exception != null) {
                throw new RuntimeException(exception);
            }
        });
    }

    @Value
    @Builder
    @Jacksonized
    public static class Message {
        String body;
    }
}
