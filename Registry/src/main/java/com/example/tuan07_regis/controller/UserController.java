package com.example.tuan07_regis.controller;

import com.example.tuan07_regis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/template/users")
    public List<User> getUserList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(headers);

        return restTemplate.exchange("http://localhost:8080/users", HttpMethod.GET, entity, List.class).getBody();
    }


    @RequestMapping(value = "/template/user", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(user, headers);

        return restTemplate.exchange(
                "http://localhost:8080/register", HttpMethod.POST, entity, User.class).getBody();

    }
}
