package ru.max.spring.cloud.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.binder.kafka.utils.DlqDestinationResolver;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCloudKafkaApplication {

    private static final String DLQ_SUFFIX = "-dlq";

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudKafkaApplication.class, args);
    }

    @Bean
    public DlqDestinationResolver dlqDestinationResolver() {
        return (rec, ex) -> {
            var topic = rec.topic();
            return topic + DLQ_SUFFIX;
        };
    }
}
