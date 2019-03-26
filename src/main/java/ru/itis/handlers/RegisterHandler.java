package ru.itis.handlers;

import ru.itis.entities.User;
import ru.itis.services.UserService;

import java.util.Scanner;

public class RegisterHandler {
    private UserService userService = new UserService();
    private ProfileHandler profileHandler = new ProfileHandler();
    Scanner sc = new Scanner(System.in);
    public void respond(){
        System.out.println(userService.getCurrentUser());
        if (userService.getCurrentUser() != null) {
            profileHandler.respond(userService.getCurrentUser());
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
                profileHandler.respond(userService.getCurrentUser());
            } else {
                System.out.println("choose one of r-register l-login s-search e-exit");
            }
        }
    }
}
