package ru.itis.db.dao;

import ru.itis.entities.User;

import java.util.List;

public interface UserDAOInterface {
    User getUserById(int id);
    List<User> getAllUsers();
    void addUser(String login, String email, String password);
    User getUserByLogin(String login);
}
