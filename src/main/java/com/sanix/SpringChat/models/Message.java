package com.sanix.SpringChat.models;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String content;
    private Date created=new Date();

    @ManyToOne
    @JoinColumn(name="fk_author")
    private User author;

    @Enumerated(value=EnumType.STRING)
    private MessageType messageType;

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", author=" + author +
                ", messageType=" + messageType +
                '}';
    }
}
