package ru.max.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.max.kafka.InputMessagesController.Message;

import java.util.Properties;

@Configuration
public class KafkaProducerConfig {

    @Bean
    @ConfigurationProperties("kafka.producer")
    public Properties producerProps() {
        return new Properties();
    }

    @Bean
    public KafkaProducer<String, Message> kafkaProducer(Properties producerProps) {
        return new KafkaProducer<>(producerProps);
    }
}
