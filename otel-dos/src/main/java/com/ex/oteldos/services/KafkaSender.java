package com.ex.oteldos.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    String kafkaTopic = "kafka_topic";

    public void send(String message) {
        kafkaTemplate.send(kafkaTopic, message);
        LOGGER.info("Message sent to the Kafka Topic java_in_use_topic Successfully");
    }
}