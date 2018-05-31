package com.webstudent.platform.controller;


import com.webstudent.platform.model.User;
import com.webstudent.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/get/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        return userRepository.getUserByEmail(email);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        Optional.ofNullable(user.getSubjectList())
                .orElse(new ArrayList<>())
                .forEach(subject -> subject.setUser(user));

        return userRepository.save(user);
    }
}
