package com.example.tuan07.controller;

import com.example.tuan07.model.User;
import com.example.tuan07.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(HttpServletResponse res, @RequestParam(name = "userName") String userName, @RequestParam(name = "password") String password) throws IOException {
        User user = userRepository.findByUserName(userName);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return "correct";
            } else {
                res.sendError(401);
            }
        } else {
            res.sendError(404);
        }
        return "error";
    }
    @PostMapping("/register")
    public User register(HttpServletResponse res, @RequestBody User user) throws IOException {
        User user1 = userRepository.findByUserName(user.getUserName());
        System.out.println(user1);
        if (user1 != null) {
            res.sendError(400);
           return null;
        } else {
            return userRepository.save(user);
        }

    }


    @GetMapping("/users")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/user")
    public User getUserByUserName(@RequestParam(name = "userName") String name){
        return userRepository.findByUserName(name);
    }

}
