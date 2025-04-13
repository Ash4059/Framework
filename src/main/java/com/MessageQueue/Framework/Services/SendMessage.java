package com.MessageQueue.Framework.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendMessage {

    private final KafkaTemplate<Integer, Object> kafkaTemplate;

    private final String nestedObj = "{"
            + "\"user\": {"
            + "    \"id\": 12345,"
            + "    \"name\": \"John Doe\","
            + "    \"details\": {"
            + "        \"email\": \"john.doe@example.com\","
            + "        \"phone\": \"+1234567890\","
            + "        \"address\": {"
            + "            \"line1\": \"123 Main St\","
            + "            \"line2\": \"Apt 4B\","
            + "            \"city\": \"Metropolis\","
            + "            \"state\": \"NY\","
            + "            \"postalCode\": \"10001\","
            + "            \"country\": \"USA\""
            + "        },"
            + "        \"preferences\": {"
            + "            \"language\": \"en\","
            + "            \"notifications\": {"
            + "                \"email\": true,"
            + "                \"sms\": false,"
            + "                \"push\": true"
            + "            }"
            + "        }"
            + "    }"
            + "}}";

    public boolean JsonValidator(String data){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.readTree(data);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @PostConstruct
    public void sendMessageAtEnd(){
        Object jsonObject = null;
        try {
            jsonObject = nestedObj;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
//        SendMessageToKafka("defaultTopic", jsonObject);
    }

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
