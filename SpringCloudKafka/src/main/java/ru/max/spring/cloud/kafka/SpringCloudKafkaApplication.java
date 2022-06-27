package ru.max.spring.cloud.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaProducerProperties;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver.NewDestinationBindingCallback;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class SpringCloudKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudKafkaApplication.class, args);
    }

    @Bean
    @GlobalChannelInterceptor(patterns = "*")
    public ChannelInterceptor customInterceptor(BindingServiceProperties bindingServiceProperties) {
        return new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                return message;
            }
        };
    }

    @Bean
    public NewDestinationBindingCallback<KafkaProducerProperties> dynamicConfigurer(BindingServiceProperties bindingServiceProperties) {
        return (name, channel, props, extended) -> {
            final String[] dynamicDestinations = bindingServiceProperties.getDynamicDestinations();
            boolean dynamicAllowed = ObjectUtils.isEmpty(dynamicDestinations)
                    || ObjectUtils.containsElement(dynamicDestinations, name);
            if (!dynamicAllowed) {
                throw new IllegalArgumentException("Destination " + name + " is not allowed");
            }
        };
    }
}
