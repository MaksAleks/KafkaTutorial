package ru.max.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class KafkaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApiApplication.class, args);
    }

}
