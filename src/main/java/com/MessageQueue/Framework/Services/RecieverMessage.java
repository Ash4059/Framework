package com.MessageQueue.Framework.Services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RecieverMessage {

    @KafkaListener(topics = "#{T(com.MessageQueue.Framework.Utils.TOPIC_NAME).REGISTER_USER.getValue()}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeRegisterMessage(Object message) {
        System.out.println("Consumed message: " + message.toString());

        // Add your message processing logic here
        if (message.toString().contains("alert")) {
            System.out.println("Alert message received!");
        }
    }

    @KafkaListener(topics = "#{T(com.MessageQueue.Framework.Utils.TOPIC_NAME).DELETE_USER.getValue()}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeDeleteMessage(Object message) {
        System.out.println("Consumed message: " + message.toString());

        // Add your message processing logic here
        if (message.toString().contains("alert")) {
            System.out.println("Alert message received!");
        }
    }

    @KafkaListener(topics = "#{T(com.MessageQueue.Framework.Utils.TOPIC_NAME).UPDATE_USER.getValue()}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeUpdateMessage(Object message) {
        System.out.println("Consumed message: " + message.toString());

        // Add your message processing logic here
        if (message.toString().contains("alert")) {
            System.out.println("Alert message received!");
        }
    }

}
