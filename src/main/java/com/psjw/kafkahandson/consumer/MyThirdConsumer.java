package com.psjw.kafkahandson.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psjw.kafkahandson.model.MyMessage;
import com.psjw.kafkahandson.model.Topic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyThirdConsumer {


    @KafkaListener(
            topics = {Topic.MY_JSON_TOPIC},
            groupId = "batch-test-consumer-group",
            containerFactory = "batchKafkaListenerContainerFactory"
    )

    public void accept(List<ConsumerRecord<String, String>> messages) {
        ObjectMapper objectMapper = new ObjectMapper();
        messages.forEach(message -> {
            MyMessage myMessage;
            try {
                myMessage = objectMapper.readValue(message.value(), MyMessage.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ㄴ [Third Consumer] Value " + myMessage + " / Offset - " + message.offset() + " / Partition - " + message.partition());
        });
    }
}
