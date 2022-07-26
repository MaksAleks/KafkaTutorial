package ru.max.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class ConsumerSimpleExample {

    public static void main(String[] args) {

        var consumerConfig = new Properties();
        consumerConfig.put("bootstrap.servers", "localhost:19092,localhost:29092");
        consumerConfig.put("group.id", "G1");
        consumerConfig.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerConfig.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        var consumer = new KafkaConsumer<String, String>(consumerConfig);

        consumer.subscribe(Collections.singleton("Test"));

        Duration timeout = Duration.ofMillis(100);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(timeout);
            for (ConsumerRecord<String, String> record : records) {
                log.info("\n\ttopic = {}\n\tpartition = {}\n\toffset = {}\n\tkey = {}\n\tmessage = {}",
                        record.topic(),
                        record.partition(),
                        record.offset(),
                        record.key(),
                        record.value()
                );
            }
            consumer.commitAsync(new OffsetCommitCallback() {
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                    if (exception != null) {
                        log.error("Error while committing offset {}", offsets, exception);
                    }
                }
            });
        }
    }
}
