package com.ex.otelcuatro.configurations;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class KafkaConfig {
    @Value(value = "${kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${kafka.consumer.group-id}")
    private String consumerGroupId;


    @Bean
    public ConsumerFactory<String, String> consumerFactory()
    {
        // Map for configuration
        Map<String, Object> config = new HashMap<>();
        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG,
                consumerGroupId);
        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    // Creating a Listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory
    concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<
                String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}