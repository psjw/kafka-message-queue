package com.psjw.kafkahandson.api;

import com.psjw.kafkahandson.model.MyMessage;
import com.psjw.kafkahandson.producer.MyProducer;
import com.psjw.kafkahandson.producer.MySecondProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MyController {

    private final MyProducer myProducer;
    private final MySecondProducer mySecondProducer;


    @RequestMapping("/hello")
    String hello() {
        return "Hello World";
    }

    @PostMapping("/message")
    void message(
            @RequestBody MyMessage message
    ){
        myProducer.sendMessage(message);
    }


    @PostMapping("/second-message/{key}")
    void message(
            @PathVariable String key,
            @RequestBody String message
    ){
        mySecondProducer.sendMessageWithKey(key, message);
    }
}
