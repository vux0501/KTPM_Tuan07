package com.example.tuan07.repository;

import com.example.tuan07.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByUserName(String userName);
}
