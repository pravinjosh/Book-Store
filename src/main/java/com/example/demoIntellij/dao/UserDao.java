package com.example.demoIntellij.dao;

import com.example.demoIntellij.models.User;
import com.example.demoIntellij.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public User signUp(User user){
       return userRepository.save(user);
    }
    public User logIn(String email,String password){
        Query query=Query.query(Criteria.where("email").is(email).and("password").is(password));
        User user=mongoTemplate.findOne(query,User.class,"user");
        return user;
    }
}
