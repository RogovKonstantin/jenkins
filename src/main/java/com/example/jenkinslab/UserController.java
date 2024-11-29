package com.example.jenkinslab;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User("1", "Alice"));
        users.add(new User("2", "Bob"));

        return users;
    }
}