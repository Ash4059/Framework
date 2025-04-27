package com.MessageQueue.Framework.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class JacksonKafkaDeserializer<T> implements Deserializer<T> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Class<T> targetType;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey){
        String targetTypeName = (String) configs.get("target.type");
        try {
            if(targetTypeName != null){
                this.targetType = (Class<T>) Class.forName(targetTypeName);
            }
        }catch (ClassNotFoundException e){
            throw new RuntimeException("Failed to configure JacksonKafkaDeserializer", e);
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        System.out.println("Deserializing message from topic: " + topic + " targetType is " + this.targetType);
        try {
            return objectMapper.readValue(data, this.targetType);
        }catch (Exception e){
            throw new RuntimeException("Failed to deserialized data", e);
        }
    }

}
