package com.MessageQueue.Framework.Utils;

import com.MessageQueue.Framework.Model.User;
import com.MessageQueue.Framework.Services.UserService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class TaskScheduling {

    private final UserService userService;

    public TaskScheduling(UserService userService){
        this.userService = userService;
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void reportCurrentTime(){
        List<User> recentUser = userService.getRecentUsers();
        System.out.println("Recent users are :- ");
        System.out.println(recentUser.toString());
    }
}
