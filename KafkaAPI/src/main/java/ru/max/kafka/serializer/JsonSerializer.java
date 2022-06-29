package ru.max.kafka.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;
import ru.max.kafka.InputMessagesController.Message;

public class JsonSerializer implements Serializer<Message> {

    @Override
    @SneakyThrows
    public byte[] serialize(String topic, Message data) {
        var om = new ObjectMapper();
        return om.writeValueAsBytes(data);
    }
}
