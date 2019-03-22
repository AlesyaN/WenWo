package ru.itis.db;

import org.apache.commons.codec.digest.DigestUtils;
import ru.itis.db.dao.UserDAO;
import ru.itis.entities.User;

public class TestMain {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
//        User user = userDAO.getUser(1);
//        System.out.println(user.getId() + " " + user.getLogin() + " " + user.getPassword() + " " + user.getEmail());

//        List<User> users = userDAO.getAllUsers();
//        for (User u: users) {
//            System.out.println(u.toString());
//        }

//        userDAO.addUser("peter", "peter@mail.ru", "hello");
        User user = userDAO.getUserByLogin("peter");
        System.out.println(user.toString());


    }
}
