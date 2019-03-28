package ru.itis.handlers;

import ru.itis.entities.User;
import ru.itis.services.UserService;

import java.util.Scanner;

public class LoginHandler implements HandlerInterface{
    private UserService userService = new UserService();
    Scanner sc = new Scanner(System.in);

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
