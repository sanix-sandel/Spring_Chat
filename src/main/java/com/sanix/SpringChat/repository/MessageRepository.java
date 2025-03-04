package com.sanix.SpringChat.repository;

import com.sanix.SpringChat.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}
