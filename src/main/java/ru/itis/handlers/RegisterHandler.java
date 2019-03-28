package ru.itis.handlers;

import ru.itis.entities.User;
import ru.itis.services.UserService;

import java.util.Scanner;

public class RegisterHandler implements HandlerInterface {
    private UserService userService = new UserService();
    Scanner sc = new Scanner(System.in);

    public String respond(){
        if (userService.getCurrentUser() != null) {
            return "/profile " + userService.getCurrentUser().getId();
        } else {
            System.out.println("enter username");
            String login = sc.nextLine();
            System.out.println("enter email");
            String email = sc.nextLine();
            System.out.println("enter password");
            String password = sc.nextLine();
            User new_user = userService.register(login, email, password);
            if (new_user != null) {
                userService.authorize(new_user);
                return "/profile " + new_user.getId();
            } else {
                System.out.println("Error while register");
                return "/register";
            }
        }
    }
}
