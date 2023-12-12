package com.example.demoIntellij.services;

import com.example.demoIntellij.dao.UserDao;
import com.example.demoIntellij.models.User;
import com.example.demoIntellij.responseDTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UserDao userDao;
    public ResponseDTO singUp(User user){
        if (user!=null){
           User user1= userDao.signUp(user);
           return new ResponseDTO(HttpStatus.CREATED.value(), "User Registered Succesfully","SUCCESS",user1);
        }
        return new ResponseDTO(HttpStatus.NO_CONTENT.value(), "No Content inside the User Request","FAILED",null);
    }
    public ResponseDTO logIn(String email,String password){
        User user=userDao.logIn(email,password);
        if (user!=null){
            return new ResponseDTO(HttpStatus.FOUND.value(),"User Found","SUCCESS",user);
        }
        return new ResponseDTO(HttpStatus.NO_CONTENT.value(), "Invalid User","FAILED",null);
    }
}
