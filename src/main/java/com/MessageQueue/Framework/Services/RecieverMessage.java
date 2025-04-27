package com.MessageQueue.Framework.Services;

import com.MessageQueue.Framework.Model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RecieverMessage {

    private final UserService userService;

    public RecieverMessage(UserService userService){
        this.userService = userService;
    }

    @KafkaListener(topics = "#{T(com.MessageQueue.Framework.Utils.TOPIC_NAME).REGISTER_USER.getValue()}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeRegisterMessage(User user) {
        System.out.println("Consumed message: " + user.toString());
        try {
            this.userService.saveUser(user);
        }catch (Exception e){
            System.out.println("Server internal error " + e.getMessage());
        }
    }

    @KafkaListener(topics = "#{T(com.MessageQueue.Framework.Utils.TOPIC_NAME).DELETE_USER.getValue()}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeDeleteMessage(User user) {
        System.out.println("Consumed message: " + user.toString());
        try {
            this.userService.deleteUserById(user.getId());
        }catch (Exception e){
            System.out.println("Server internal error" + e.getMessage());
        }
    }

    @KafkaListener(topics = "#{T(com.MessageQueue.Framework.Utils.TOPIC_NAME).UPDATE_USER.getValue()}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeUpdateMessage(User user) {
        try{
            this.userService.updateUser(user);
        }catch (Exception e){
            System.out.println("Server internal error" + e.getMessage());
        }
    }

}
