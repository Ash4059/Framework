package com.MessageQueue.Framework.Controller;

import com.MessageQueue.Framework.Model.User;
import com.MessageQueue.Framework.Services.UserService;

import java.util.List;

import com.MessageQueue.Framework.Utils.TOPIC_NAME;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final KafkaTemplate<Integer, Object> kafkaTemplate;
    private final UserService userService;

    public UserController(KafkaTemplate<Integer, Object> kafkaTemplate, UserService userService){
        this.kafkaTemplate = kafkaTemplate;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user, @RequestHeader("User-Agent") String userAgent){
        try {
            this.kafkaTemplate.send(TOPIC_NAME.REGISTER_USER.getValue(), user);
            return new ResponseEntity<>("User data consumed properly", HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception while sending data " + user.toString());
            return new ResponseEntity<>("Exception while sending data " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findUserById(id).get(), HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUserById(@RequestBody User user, @RequestHeader("User-Agent") String userAgent){
        this.kafkaTemplate.send(TOPIC_NAME.DELETE_USER.getValue(), user);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUserById(@RequestBody User user, @PathVariable Long id,
                                                 @RequestHeader("User-Agent") String userAgent){
        user.setId(id);
        this.kafkaTemplate.send(TOPIC_NAME.UPDATE_USER.getValue(), user);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
