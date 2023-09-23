package com.baidya.kafka.config.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void postMessage(String topicName, String data){
        LOGGER.info("TOPIC: {} - MESSAGE: {}", topicName, data);
        kafkaTemplate.send(topicName, data);
    }
}
