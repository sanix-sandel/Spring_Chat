package com.sanix.SpringChat.controllers;

import com.sanix.SpringChat.DTO.MessageDTO;
import com.sanix.SpringChat.DTO.MessageType;
import com.sanix.SpringChat.models.Message;
import com.sanix.SpringChat.models.User;
import com.sanix.SpringChat.services.MessageService;
import com.sanix.SpringChat.services.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class Chat {

    private final UserService userService;
    private final MessageService messageService;

    public Chat(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload MessageDTO messageDTO){
        if(messageDTO.getType()== MessageType.JOIN){

            User user=new User();
            user.setUsername(messageDTO.getUsername());
            userService.save(user);
            System.out.println(user);
            Message message=new Message();
            message.setContent(messageDTO.getContent());
            System.out.println(message);
            messageService.save(message);
            return message;

        }else{
            Message message=new Message();
            User user=userService.findByUsername(messageDTO.getUsername());
            message.setAuthor(user);
            message.setContent(messageDTO.getContent());
            System.out.println(message);
            messageService.save(message);
            return message;
        }


    }

    @MessageMapping("/chat.joinChat")
    @SendTo("/topic/public")
    public MessageDTO joinChat(@Payload MessageDTO message,
                            SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", message.getUsername());
        return message;
    }

}
