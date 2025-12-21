package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUserRating(Long id, double rating);

    User login(String email, String password);
}
