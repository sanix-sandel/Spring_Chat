package com.sanix.SpringChat.event;

import com.sanix.SpringChat.models.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class EventHandler {

    private static Logger logger= LoggerFactory.getLogger(EventHandler.class);
    private SimpMessagingTemplate simpMessagingTemplate;

    public EventHandler(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @HandleAfterCreate
    public void handleToSave(MessageDTO message){
        this.simpMessagingTemplate.convertAndSend("/topic", message);
        logger.info(">> New message sent by "+message.getUsername()+" ");
    }
}
