package com.MessageQueue.Framework.Controller;

import com.MessageQueue.Framework.Services.KafkaTopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaControllers {

    private final KafkaTopicService kafkaTopicService;

    public KafkaControllers(KafkaTopicService kafkaTopicService){
        this.kafkaTopicService = kafkaTopicService;
    }

    @GetMapping("/describe-topic")
    public ResponseEntity<String> describeTopic(@RequestParam String topicName){
        try{
            this.kafkaTopicService.describeTopic(topicName);
            return new ResponseEntity<>("Description for topic: " + topicName, HttpStatus.OK);
        }catch (Exception e){
            // Return an error response with the status code
            return new ResponseEntity<>("Error describing topic: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}