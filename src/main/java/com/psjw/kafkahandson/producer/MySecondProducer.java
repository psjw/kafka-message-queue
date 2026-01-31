package com.psjw.kafkahandson.producer;

import com.psjw.kafkahandson.model.MyMessage;
import com.psjw.kafkahandson.model.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MySecondProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageWithKey(String key, String myMessage) {
        kafkaTemplate.send(Topic.MY_SECOND_TOPIC, myMessage);
    }

}
