package com.MessageQueue.Framework.Controller;

import com.MessageQueue.Framework.Model.User;
import com.MessageQueue.Framework.Services.KafkaTopicService;
import com.MessageQueue.Framework.Services.SendMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaControllers {

    private final KafkaTopicService kafkaTopicService;
    private final SendMessage sendMessage;

    public KafkaControllers(KafkaTopicService kafkaTopicService, SendMessage sendMessage){
        this.kafkaTopicService = kafkaTopicService;
        this.sendMessage = sendMessage;
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

    @PostMapping("/user")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        try {
            this.sendMessage.SendMessageToKafka("defaultTopic", user);
            return new ResponseEntity<>("User data consumed properly", HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception while sending data " + user.toString());
            return new ResponseEntity<>("Exception while sending data " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}