package ru.itis.handlers;

import ru.itis.entities.User;
import ru.itis.services.UserService;

import java.util.Scanner;

public class SearchHandler {
    Scanner scanner = new Scanner(System.in);
    private UserService userService = new UserService();
    private ProfileHandler profileHandler = new ProfileHandler();

    public void respond() {
        System.out.println("enter your friend username");
        User srch_user = userService.getUserByLogin(scanner.nextLine());
        if (srch_user != null) {
            profileHandler.respond(srch_user);
        } else {
            System.out.println("choose one of r-register l-login s-search e-exit");
        }
    }
}
