package com.MessageQueue.Framework.Repository;

import com.MessageQueue.Framework.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);

    @Query(value = "SELECT * FROM user ORDER BY created_at DESC LIMIT 10", nativeQuery = true)
    List<User> getRecentUsers();
}
