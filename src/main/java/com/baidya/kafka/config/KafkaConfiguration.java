package com.baidya.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConfiguration.class);
    @Bean
    public MessageListener<String, String> messageListener(){
        return (consumerRecord -> {
            LOGGER.info("Consumed record: topic:{} value:{}", consumerRecord.topic(), consumerRecord.value());
        });
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer(){
        KafkaMessageListenerContainer<String, String > container = new KafkaMessageListenerContainer<>(consumerFactory(), containerProperties());
        return container;
    }

    @Bean
    public ContainerProperties containerProperties() {
        ContainerProperties containerProperties = new ContainerProperties("location.service");
        containerProperties.setMessageListener(messageListener());
        containerProperties.setAckMode(ContainerProperties.AckMode.MANUAL);
        return containerProperties;
    }

    @Bean
    public ConsumerFactory consumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "loc-grp");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        DefaultKafkaConsumerFactory defaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory(properties);
        return  defaultKafkaConsumerFactory;
    }
}
