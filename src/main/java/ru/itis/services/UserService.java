package ru.itis.services;

import org.apache.commons.codec.digest.DigestUtils;
import ru.itis.db.SessionSignleton;
import ru.itis.db.dao.UserDAO;
import ru.itis.entities.User;

import java.util.Map;

public class UserService {
    private final Map<String, User> session = SessionSignleton.getSession();
    private UserDAO userDAO = new UserDAO();

    public User getCurrentUser() {
        return session.get("current_user");
    }

    public User register(String login, String email, String password) {
        if (login != null && loginIsUnique(login)) {
            userDAO.addUser(login, email, DigestUtils.md5Hex(password));
            User new_user = userDAO.getUserByLogin(login);
            authorize(new_user);
            return new_user;
        } else return null;
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
        session.put("current_user", user);
    }

    public User getUserByLogin(String login) {
        if (login != null)
            return userDAO.getUserByLogin(login);
        else return null;
    }
}
