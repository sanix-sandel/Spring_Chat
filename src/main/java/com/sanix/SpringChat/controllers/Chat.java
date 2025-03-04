package com.sanix.SpringChat.controllers;

import com.sanix.SpringChat.models.MessageDTO;
import com.sanix.SpringChat.models.Message;
import com.sanix.SpringChat.models.User;
import com.sanix.SpringChat.services.MessageService;
import com.sanix.SpringChat.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class Chat {

    private final UserService userService;
    private final MessageService messageService;
    private static Logger logger= LoggerFactory.getLogger(Chat.class);

    public Chat(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload MessageDTO messageDTO){

        Message message=new Message();
        User user=userService.findByUsername(messageDTO.getUsername());
        message.setAuthor(user);
        message.setContent(messageDTO.getContent());
        message.setMessageType(messageDTO.getType());
        System.out.println(messageDTO);
        messageService.save(message);
        logger.info(">>>"+message.getAuthor().getUsername()+ " sent a message");
        return message;

    }

    @MessageMapping("/chat.joinChat")
    @SendTo("/topic/public")
    public MessageDTO joinChat(@Payload MessageDTO message,
                            SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", message.getUsername());
        User user=new User();
        user.setUsername(message.getUsername());
        userService.save(user);
        logger.info(message.getUsername()+" joined the chat ! Great !");
        return message;
    }

}
