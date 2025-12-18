package com.example.demo.service;

import java.util.List;
import com.example.demo.model.User;

public interface UserService {

    User save(User user);

    User findById(Long id);

    List<User> findAll();

    void delete(Long id);

    User findByEmail(String email);
}