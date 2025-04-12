package com.MessageQueue.Framework.Services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RecieverMessage {

    @KafkaListener(topics = "defaultTopic", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeMessage(Object message) {
        System.out.println("Consumed message: " + message.toString());

        // Add your message processing logic here
        if (message.toString().contains("alert")) {
            System.out.println("Alert message received!");
        }
    }


}
