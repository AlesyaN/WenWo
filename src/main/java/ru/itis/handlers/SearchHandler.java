package ru.itis.handlers;

import ru.itis.entities.User;
import ru.itis.services.UserService;

import java.util.Scanner;

public class SearchHandler implements HandlerInterface{
    Scanner scanner = new Scanner(System.in);
    private UserService userService = new UserService();
    private ProfileHandler profileHandler = new ProfileHandler();

    public String respond() {
        System.out.println("enter your friend username");
        User srch_user = userService.getUserByLogin(scanner.nextLine());
        if (srch_user != null) {
            return "/profile " + srch_user.getId();
        } else {
            System.out.println("No such user");
            return "/search";
        }
    }
}
