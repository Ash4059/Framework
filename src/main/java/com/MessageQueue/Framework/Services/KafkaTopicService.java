package com.MessageQueue.Framework.Services;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class KafkaTopicService {

    private final KafkaAdmin kafkaAdmin;

    public KafkaTopicService(KafkaAdmin kafkaAdmin){
        this.kafkaAdmin = kafkaAdmin;
    }

    public void describeTopic(String topicName) throws Exception{
        try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Collections.singletonList(topicName));
            Map<String, TopicDescription> topicDescriptionMap = describeTopicsResult.allTopicNames().get();
            for(Map.Entry<String, TopicDescription> entry : topicDescriptionMap.entrySet()){
                System.out.println("Topic name : " + entry.getKey());
                System.out.println("Topic description : " + entry.getValue());
            }
        }
    }

}
