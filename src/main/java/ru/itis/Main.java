package ru.itis;

import ru.itis.db.dao.UserDAO;
import ru.itis.entities.User;
import ru.itis.handlers.*;
import ru.itis.services.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App app = App.getApp();
        app.run();
    }
}
