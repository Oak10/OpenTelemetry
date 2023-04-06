package com.ex.otelcuatro.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "kafka_topic", groupId = "group_id", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consume(String message) {
        // Print statement
        LOGGER.info("kafka message <{}> ", message);
    }
}