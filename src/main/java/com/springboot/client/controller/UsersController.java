package com.springboot.client.controller;

import com.springboot.client.model.CreateUserRequest;
import com.springboot.client.model.User;
import com.springboot.client.model.UserDetails;
import com.springboot.client.service.UserClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UsersController {
    private static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserClientService userClientService;

    private int page = 0;
    private int size = 50;

    List<User> users;

    @GetMapping(value = "/users")
    public String list(Model model) {
        users = userClientService.list(page, size);
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping(value = "/users/{id}")
    public String getUser(@PathVariable("id") String id, Model model) {
        User user = userClientService.get(id);
        model.addAttribute("user", user);
        return "details";
    }

    @GetMapping(value = "/users/create")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "details";
    }

    @RequestMapping(value="/users/update", method=RequestMethod.POST)
    public String updateUser(@ModelAttribute User user, Model model) {
        if (StringUtils.isEmpty(user.getId())){
            User newUser = userClientService.create(user);
            if(newUser == null){
                logger.warn("Failed to create new users.");
            } else {
                logger.info("New user created: " + newUser.toString());
            }
        } else {
            User updatedUser = userClientService.update(user.getId(), user);
            if(updatedUser == null) {
                logger.warn("Failed to update user.");
            } else {
                logger.info("User updated: " + updatedUser.toString());
            }
        }
        return "redirect:/users";
    }

    @GetMapping(value = "/users/delete/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        userClientService.delete(id);
        return "redirect:/users";
    }

}
