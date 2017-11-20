package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByEmail(String email);
    

}
