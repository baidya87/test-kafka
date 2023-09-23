package com.baidya.kafka.config.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);
    //@KafkaListener(topics = "location.service", groupId = "default")
    public void consumeMessages(ConsumerRecord<String, String> consumerRecord){
        LOGGER.info("Message consumed : {} from topic : {}", consumerRecord.toString(), consumerRecord.topic());
        //String message = consumerRecord.toString();
    }

}
