package com.example.testtask.service;

import com.example.testtask.entity.User;
import com.example.testtask.exceptions.UserIsAlreadyExistException;

public interface UserService {
    void createUser(User user);
    void changeUserName(String oldName, String newName) throws UserIsAlreadyExistException;
    void createUserFromRegistrationForm(String username, String password);
}
