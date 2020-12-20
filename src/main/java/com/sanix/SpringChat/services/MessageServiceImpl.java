package com.sanix.SpringChat.services;

import com.sanix.SpringChat.models.Message;
import com.sanix.SpringChat.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
