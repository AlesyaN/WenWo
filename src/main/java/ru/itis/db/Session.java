package ru.itis.db;

import ru.itis.entities.User;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private static Session session;
    private User currentUser;
    private Session() {
    }

    public static Session getSession() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}
