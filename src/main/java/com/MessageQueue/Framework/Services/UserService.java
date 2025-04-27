package com.MessageQueue.Framework.Services;

import com.MessageQueue.Framework.Model.User;
import com.MessageQueue.Framework.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public boolean deleteUserById(Long id){
        User user = entityManager.find(User.class, id);
        if(!Objects.isNull(user)){
            entityManager.remove(user);
            return true;
        }
        return false;
    }

    public void updateUser(User user){
        entityManager.merge(user);
    }
}
