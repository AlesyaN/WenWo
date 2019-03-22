package ru.itis.db;

import ru.itis.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SessionSignleton {
    private static Map<String, User> session;

    public static Map<String, User> getSession() {
        if (session == null) {
            session = new HashMap<>();
        }
        return session;
    }
}
