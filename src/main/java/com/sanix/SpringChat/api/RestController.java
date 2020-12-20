package com.sanix.SpringChat.api;

import com.sanix.SpringChat.models.Message;
import com.sanix.SpringChat.models.User;
import com.sanix.SpringChat.services.MessageService;
import com.sanix.SpringChat.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private final UserService userService;
    private final MessageService messageService;

    public RestController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService=messageService;
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/messages")
    List<Message> getMessages(){
        return messageService.findAll();
    }
}
