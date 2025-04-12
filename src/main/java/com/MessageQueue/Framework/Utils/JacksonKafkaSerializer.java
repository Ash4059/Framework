package com.MessageQueue.Framework.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class JacksonKafkaSerializer<T> implements Serializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, T data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        }catch (Exception e){
            throw new RuntimeException("Error serializing JSON " + e.getMessage());
        }
    }
}
