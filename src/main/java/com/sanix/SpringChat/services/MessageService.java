package com.sanix.SpringChat.services;

import com.sanix.SpringChat.models.Message;

import java.util.List;

public interface MessageService {
    void save(Message message);
    List<Message> findAll();
}
