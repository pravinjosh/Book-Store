package com.example.demoIntellij.controller;

import com.example.demoIntellij.models.User;
import com.example.demoIntellij.responseDTO.ResponseDTO;
import com.example.demoIntellij.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices userServices;
    @PostMapping("/signUp")
    private ResponseDTO signUp(@RequestBody User user){
       return userServices.singUp(user);
    }
    @PostMapping("/login")
    public ResponseDTO logIn(@RequestBody String email,@RequestBody String password){
        return userServices.logIn(email,password);
    }

}
