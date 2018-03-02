package com.nikolaev.controller;

import com.nikolaev.service.UserService;
import com.nikolaev.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @PatchMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        User foundUser = userService.getUserById(id);
        foundUser.setLogin(user.getLogin());
        foundUser.setPassword(user.getPassword());
        return userService.updateUser(foundUser);
    }

    @PatchMapping("users/{id}/block")
    public User block(@PathVariable("id") Long id) {
        User foundUser = userService.getUserById(id);
        return userService.block(foundUser);
    }

    @PatchMapping("users/{id}/unlock")
    public User unlock(@PathVariable("id") Long id) {
        User foundUser = userService.getUserById(id);
        return userService.unlock(foundUser);
    }

}
