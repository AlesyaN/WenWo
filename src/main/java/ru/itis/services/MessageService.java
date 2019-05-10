package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.db.dao.MessageDAO;
import ru.itis.db.dao.MessageDAOInterface;
import ru.itis.entities.Message;
import ru.itis.entities.User;

import java.util.List;

@Component
public class MessageService {

    private final MessageDAOInterface messageDAO;

    @Autowired
    public MessageService(MessageDAOInterface messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }

    public List<Message> getMessagesByUsers(User sender, User receiver) {
        return messageDAO.getMessagesByUsers(sender,receiver);
    }

    public void addMessage(Message message) {
        messageDAO.addMessage(message.getSender(), message.getReceiver(), message.getText());
    }

    public boolean deleteMessage(int id, User sender) {
        if (messageDAO.getMessageById(id).getSender().getLogin().equals(sender.getLogin())) {
            messageDAO.deleteMessage(id);
            return true;
        } else {
            return false;
        }
    }

}
