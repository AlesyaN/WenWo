package ru.itis.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.entities.User;
import ru.itis.services.UserService;

import java.util.Scanner;

@Component
public class SearchHandler implements HandlerInterface {
    Scanner scanner = new Scanner(System.in);

    private final UserService userService;

    @Autowired
    public SearchHandler(UserService userService) {
        this.userService = userService;
    }

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
