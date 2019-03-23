package ru.itis.db;

import ru.itis.entities.User;

import java.util.HashMap;
import java.util.Map;

public class SessionSingleton {
    private static Map<String, User> session;

    public static Map<String, User> getSession() {
        if (session == null) {
            session = new HashMap<>();
        }
        return session;
    }
}
