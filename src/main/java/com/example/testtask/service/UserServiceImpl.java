package com.example.testtask.service;

import com.example.testtask.entity.User;
import com.example.testtask.exceptions.UserIsAlreadyExistException;
import com.example.testtask.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void createUser(User user) {
        repository.save(user);
        log.info("user saved");
    }

    @Override
    public void changeUserName(String oldName, String newName) throws UserIsAlreadyExistException {
        Optional<User> oldUserName = repository.findUserByUsername(oldName);
        Optional<User> newUserName = repository.findUserByUsername(newName);
        if (oldUserName.isPresent()){
            if (newUserName.isPresent()){
                throw new UserIsAlreadyExistException("User is already present");
            }else {
                User user = oldUserName.get();
                user.setUsername(newName);
                repository.save(user);
            }
        }
    }

    @Override
    public void createUserFromRegistrationForm(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        repository.save(user);
    }
}
