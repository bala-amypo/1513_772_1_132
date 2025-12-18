package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User register(User user) {
        return repository.save(user);
    }

    @Override
    public User login(String email, String password) {
        return repository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
