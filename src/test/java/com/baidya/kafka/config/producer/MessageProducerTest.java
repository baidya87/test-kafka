package com.baidya.kafka.config.producer;

import com.baidya.kafka.config.TestKafkaApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MessageProducerTest extends TestKafkaApplicationTests {

    @Autowired
    private MessageProducer messageProducer;

    @Test
    public void testPostMessage(){
        messageProducer.postMessage("random-posts", "Hello !! Testing kafka from java");
    }

    @Test
    public void test_cat_dogs_stories_topic(){
        messageProducer.postMessage("cat.dogs.stories", "Hello !! Posting to cat dog stories.");
    }

}
