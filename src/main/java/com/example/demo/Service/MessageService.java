package com.example.demo.Service;

import com.example.demo.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.*;

import java.util.ArrayList;
import java.util.List;


@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(messages::add);
        return messages;
    }

    public Message getMessageById(int id) {
        return messageRepository.findById(id).get();
    }

    public void saveOrUpdate(Message message) {
        messageRepository.save(message);
    }

    public void delete(int id) {
        messageRepository.deleteById(id);
    }
}