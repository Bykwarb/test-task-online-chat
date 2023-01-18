package com.example.testtask.controller;

import com.example.testtask.exceptions.UserIsAlreadyExistException;
import com.example.testtask.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ChangeUserDataController {
    @Autowired
    private UserService userService;
    @GetMapping("/changes")
    public String changePage(){
        return "changes";
    }
    @PostMapping("/changes")
    public String changeUserData(String username, HttpServletRequest request, HttpSession session) throws UserIsAlreadyExistException {
        userService.changeUserName(session.getAttribute("username").toString(), username);
        session.setAttribute("username", username);
        return "redirect:/";
    }

    @ExceptionHandler(value = UserIsAlreadyExistException.class)
    public String exc(HttpSession session){
        session.setAttribute("error", "Username is already exist!");
        return "/changes";
    }

}
