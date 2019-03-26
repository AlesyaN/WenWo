package ru.itis.handlers;

import ru.itis.entities.User;
import ru.itis.services.UserService;

import java.util.Scanner;

public class LoginHandler {
    private UserService userService = new UserService();
    private ProfileHandler profileHandler= new ProfileHandler();
    Scanner sc = new Scanner(System.in);

    public void respond() {
        System.out.println(userService.getCurrentUser());
        if (userService.getCurrentUser() != null) {
            profileHandler.respond(userService.getCurrentUser());
        } else {
            System.out.println("enter login");
            String login = sc.nextLine();
            System.out.println("enter pass");
            String pass = sc.nextLine();
            User curr_user = userService.authenticate(login, pass);
            if (curr_user != null) {
                userService.authorize(curr_user);
                profileHandler.respond(curr_user);
            } else {
                System.out.println("choose one of r-register l-login s-search e-exit");
            }

        }

}
}
