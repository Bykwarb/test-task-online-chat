package com.example.testtask.exceptions;

public class UserIsAlreadyExistException extends Exception{
    public UserIsAlreadyExistException(String message) {
        super(message);
    }
}
