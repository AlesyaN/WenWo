package ru.itis;

import ru.itis.entities.User;
import ru.itis.handlers.ChatHandler;
import ru.itis.services.UserService;

import java.util.Arrays;

public class LesyaMain {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = userService.authenticate("lesya", "qwerty");
        ChatHandler chatHandler = new ChatHandler(userService.getUserByLogin("kama"));
        chatHandler.respond();


    }
}
