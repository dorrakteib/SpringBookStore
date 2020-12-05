package com.vermeg.bookstore.controllers;

import com.vermeg.bookstore.entities.User;
import com.vermeg.bookstore.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class UserRestController {


    @Autowired
    MyUserService userService;



    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user/add")
    public User addUser(@RequestBody User user, BindingResult result) throws Exception {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        return userService.addUser(user);
    }

    @PostMapping("/admin/add")
    public User addAdmin(@RequestBody User user, BindingResult result) throws Exception {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        return userService.addAdmin(user);
    }

    @DeleteMapping("/user/{id}/delete")
    public User deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
