package ru.itis.services;

import ru.itis.db.dao.MessageDAO;
import ru.itis.entities.Message;
import ru.itis.entities.User;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO = new MessageDAO();

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
