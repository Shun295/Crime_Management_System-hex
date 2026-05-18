package com.service;

import com.exception.UserNotFoundException;
import com.model.User;
import com.repository.UserRepository;

import java.sql.SQLException;

public class UserService {

    UserRepository userRepository = new UserRepository();

    public User authenticateUser(String username, String password)
            throws SQLException, UserNotFoundException {

        return userRepository.authenticateUser(username, password);
    }
}