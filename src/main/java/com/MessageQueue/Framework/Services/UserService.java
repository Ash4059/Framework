package com.MessageQueue.Framework.Services;

import com.MessageQueue.Framework.Model.User;
import com.MessageQueue.Framework.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public boolean deleteUserById(Long id){
        Optional<User> optionalUser = findUserById(id);
        if(optionalUser.isPresent()){
            userRepository.deleteById(id);
        }
        return optionalUser.isPresent();
    }

    public boolean updateUser(User user){
        boolean isDeleted = deleteUserById(user.getId());
        if(isDeleted){
            saveUser(user);
        }
        return isDeleted;
    }
}
