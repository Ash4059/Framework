package com.MessageQueue.Framework.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendMessage {

    private final KafkaTemplate<Integer, String> kafkaTemplate;

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
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(nestedObj);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        SendMessageToKafka("defaultTopic", jsonObject);
    }

    public SendMessage(KafkaTemplate<Integer, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    private String serializeData(Object data){
        if(data instanceof String){
            return (String) data;
        } else if (data instanceof JSONObject) {
            return JSONObject.valueToString(data);
        } else if(data instanceof Object []){
            return String.join(", ", (String []) data);
        }
        else {
          throw new IllegalArgumentException("Unsupported data type.");
        }
    }

    public void SendMessageToKafka(String topic, Object data){
        try {
            String serializerData = serializeData(data);
            kafkaTemplate.send(topic, serializerData);
            System.out.println("Message sent to topic" + topic);
        }catch (Exception e){
            System.out.println("Failed to send message: " + e.getMessage());
        }
    }

}
