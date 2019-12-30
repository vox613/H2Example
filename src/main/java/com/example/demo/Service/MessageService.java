package com.example.demo.Service;

import com.example.demo.Entity.MessageEntity;
import com.example.demo.Repository.MessageRepository;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MessageService implements MessageListener {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageEntity> getAllMessages() {
        List<MessageEntity> messageEntities = new ArrayList<>();
        messageRepository.findAll().forEach(messageEntities::add);
        return messageEntities;
    }

    public MessageEntity getMessageById(int id) {
        return messageRepository.findById(id).orElse(new MessageEntity());
    }

    public void saveOrUpdate(MessageEntity messageEntity) {
        messageRepository.save(messageEntity);
    }

    public void delete(int id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Consuming Message - " + new String(message.getBody()));
        String textBody = new String(message.getBody());
        MessageEntity newMessage = new MessageEntity();
        newMessage.setMessage(textBody);
        saveOrUpdate(newMessage);
        System.out.println("msg id = " + newMessage.getId());
    }
}