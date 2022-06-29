package ru.max.kafka;

import lombok.Value;

@Value
//@ConfigurationProperties("kafka.producer")
public class KafkaProducerProperties {

    String keySerializer = "org.apache.kafka.common.serialization.StringSerializer";
    String valueSerializer = "org.apache.kafka.common.serialization.StringSerializer";
    String bootstrapServers;
}
