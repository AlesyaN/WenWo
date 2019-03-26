package ru.itis.db.dao;

import ru.itis.entities.Message;
import ru.itis.entities.User;

import java.util.List;

public interface MessageDAOInterface {
    Message getMessageById(int id);
    List<Message> getAllMessages();
    List<Message> getMessagesByUsers(User user1, User user2);
    void addMessage(User sender, User receiver, String text);
    void deleteMessage(int id);
}
