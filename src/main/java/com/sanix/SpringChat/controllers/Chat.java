package com.sanix.SpringChat.controllers;

import com.sanix.SpringChat.models.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class Chat {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/chat.joinChat")
    @SendTo("/topic/public")
    public Message joinChat(@Payload Message message,
                            SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", message.getAuthor());
        return message;
    }

}
