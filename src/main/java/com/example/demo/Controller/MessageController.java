package com.example.demo.Controller;

import com.example.demo.Entity.Message;
import com.example.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @GetMapping("/messages")
    private List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{id}")
    private Message getMessage(@PathVariable("id") int id) {
        return messageService.getMessageById(id);
    }

    @DeleteMapping("/messages/{id}")
    private void deleteMessage(@PathVariable("id") int id) {
        messageService.delete(id);
    }

    @PostMapping("/messages")
    private int saveMessage(@RequestBody Message message) {
        messageService.saveOrUpdate(message);
        return message.getId();
    }
}