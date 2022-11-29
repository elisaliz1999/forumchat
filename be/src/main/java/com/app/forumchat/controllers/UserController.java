package com.app.forumchat.controllers;

import com.app.forumchat.models.auth.User;
import com.app.forumchat.services.UserService_OLD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService_OLD us;

    @PostMapping("/save")
    public User save(@RequestBody User user){
        return us.save(user);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        return us.delete(id);
    }

    @PostMapping("/update/{id}")
    public User update(@PathVariable long id, @RequestBody User user){
        return us.update(id, user);
    }
}
