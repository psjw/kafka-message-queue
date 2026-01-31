package com.psjw.kafkahandson.consumer;

import com.psjw.kafkahandson.model.MyMessage;
import com.psjw.kafkahandson.model.Topic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {


    @KafkaListener(
            topics = {Topic.MY_JSON_TOPIC},
            groupId = "test-consumer-group"
    )
    public void accept(ConsumerRecord<String, MyMessage> message) {
        System.out.println("[Main Consumer] Message arrived! - " + message.value());
    }
}
