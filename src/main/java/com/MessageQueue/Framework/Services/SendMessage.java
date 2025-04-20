package com.MessageQueue.Framework.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendMessage {

    private final KafkaTemplate<Integer, Object> kafkaTemplate;

    public SendMessage(KafkaTemplate<Integer, Object> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void SendMessageToKafka(String topic, Object data){
        try {
            kafkaTemplate.send(topic, data);
            System.out.println("Message sent to topic" + topic);
        }catch (Exception e){
            System.out.println("Failed to send message: " + e.getMessage());
        }
    }

}
