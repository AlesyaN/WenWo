package ru.itis.db;

import ru.itis.entities.User;

public class TestMain {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUser(1);
        System.out.println(user.getId() + " " + user.getLogin() + " " + user.getPassword() + " " + user.getEmail());
    }
}
