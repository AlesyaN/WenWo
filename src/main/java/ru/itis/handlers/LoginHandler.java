package ru.itis.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.entities.User;
import ru.itis.services.UserService;

import java.util.Scanner;

@Component
public class LoginHandler implements HandlerInterface{

    private final UserService userService;
    Scanner sc = new Scanner(System.in);

    @Autowired
    public LoginHandler(UserService userService) {
        this.userService = userService;
    }

    public String respond() {
        if (userService.getCurrentUser() != null) {
            return "/profile " + userService.getCurrentUser().getId();
        } else {
            System.out.println("enter login");
            String login = sc.nextLine();
            System.out.println("enter pass");
            String pass = sc.nextLine();
            User curr_user = userService.authenticate(login, pass);
            if (curr_user != null) {
                userService.authorize(curr_user);
                return "/profile " + curr_user.getId();
            } else {
                return "/login";
            }
        }

    }
}
