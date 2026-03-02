package com.psjw.kafkahandson.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psjw.kafkahandson.model.MyMessage;
import com.psjw.kafkahandson.model.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class MyProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(MyMessage myMessage)  {
        ObjectMapper objectMapper = new ObjectMapper();
        String message = null;
        try {
            message = objectMapper.writeValueAsString(myMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        kafkaTemplate.send(Topic.MY_JSON_TOPIC, String.valueOf(myMessage.getAge()), message);
    }

}
