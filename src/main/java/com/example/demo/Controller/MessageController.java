package com.example.demo.Controller;

import com.example.demo.Entity.MessageEntity;
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


    // localhost:8181/messages





    @GetMapping("/messages")
    private List<MessageEntity> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{id}")
    private MessageEntity getMessage(@PathVariable("id") int id) {
        return messageService.getMessageById(id);
    }

    @DeleteMapping("/messages/{id}")
    private void deleteMessage(@PathVariable("id") int id) {
        messageService.delete(id);
    }

    @PostMapping("/messages")
    private int saveMessage(@RequestBody MessageEntity messageEntity) {
        messageService.saveOrUpdate(messageEntity);
        return messageEntity.getId();
    }
}