package ru.itis.services;

import org.apache.commons.codec.digest.DigestUtils;
import ru.itis.db.Session;
import ru.itis.db.dao.UserDAO;
import ru.itis.entities.User;


public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User getCurrentUser() {
        return Session.getSession().getCurrentUser();
    }

    public User register(String login, String email, String password) {
        if (login != null && loginIsUnique(login) && emailIsUnique(email)) {
            userDAO.addUser(login, email, DigestUtils.md5Hex(password));
            User new_user = userDAO.getUserByLogin(login);
            authorize(new_user);
            return new_user;
        } else return null;
    }

    private boolean emailIsUnique(String email) {
        return userDAO.getUserByEmail(email) == null;
    }

    private boolean loginIsUnique(String login) {
        return userDAO.getUserByLogin(login) == null;
    }

    public User authenticate(String login, String password) {
        if (login != null) {
            User user = userDAO.getUserByLogin(login);
            if (user == null) return null;
            if (DigestUtils.md5Hex(password).equals(user.getPassword())) {
                authorize(user);
                return user;
            } else return null;
        }
        return null;
    }
    public void authorize (User user){
        Session.getSession().setCurrentUser(user);
    }

    public User getUserByLogin(String login) {
        if (login != null)
            return userDAO.getUserByLogin(login);
        else return null;
    }
    public void logOut(){
        Session.getSession().setCurrentUser(null);

    }


    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }
}
