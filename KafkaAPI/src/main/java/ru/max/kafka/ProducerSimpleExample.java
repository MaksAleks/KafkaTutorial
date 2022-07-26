package ru.max.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

@Slf4j
public class ProducerSimpleExample {

    public static void main(String[] args) throws Exception {

        var producer = simpleProducer();
        ProducerRecord<String, String> record = new ProducerRecord<>("TestTopic", "String value");
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception e) {
                if (e != null) {
                    log.error("Couldn't send message: topic = {}, partition = {}",
                            metadata.topic(),
                            metadata.partition(),
                            e
                    );
                    throw new RuntimeException(e);
                }
                log.info("Message was successfully sent");
            }
        }).get();

        var customPartitionProducer = customPartitionerProducer();
        var rec1 = new ProducerRecord<>("TestPartitionedTopic", "0","To 0 partition");
        var rec2 = new ProducerRecord<>("TestPartitionedTopic", "1", "To 1 partition");
        var error = new ProducerRecord<>("TestPartitionedTopic", "two", "Error");

        Callback callback = (metadata, e) -> System.out.println((e != null ? e.getMessage() : "Message sent"));
        customPartitionProducer.send(rec1, callback).get();
        customPartitionProducer.send(rec2, callback).get();
        customPartitionProducer.send(error, callback).get();
    }

    public static Producer<String, String> simpleProducer() {
        Properties producerConfig = new Properties();
        producerConfig.put("bootstrap.servers", "localhost:19092");
        producerConfig.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerConfig.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return new KafkaProducer<>(producerConfig);
    }

    public static Producer<String, String> customPartitionerProducer() {
        Properties producerConfig = new Properties();
        producerConfig.put("bootstrap.servers", "localhost:19092");
        producerConfig.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerConfig.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerConfig.put("partitioner.class", "ru.max.kafka.MyCustomPartitioner");

        return new KafkaProducer<>(producerConfig);
    }
}
