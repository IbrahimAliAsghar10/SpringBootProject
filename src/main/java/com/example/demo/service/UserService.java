package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

        @Autowired
        private UserRepository userRepository;

        @Cacheable(value = "users", key = "#id")
        public Optional<User> getUserById(int id) {
            System.out.println("Fetching user from database...");
            return userRepository.findById(id);
        }

        @CachePut(value = "users", key = "#user.id")
        public User saveUser(User user) {
            return userRepository.save(user);
        }

        @CacheEvict(value = "users", key = "#id")
        public void deleteUser(int id) {
            userRepository.deleteById(id);
        }

        public List<User> getAllUsers() {
            return (List<User>) userRepository.findAll();
        }
}

