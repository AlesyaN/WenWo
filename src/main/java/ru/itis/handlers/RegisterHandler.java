package ru.itis.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.entities.User;
import ru.itis.services.UserService;

import java.util.Scanner;

@Component
public class RegisterHandler implements HandlerInterface {

    private final UserService userService;
    Scanner sc = new Scanner(System.in);

    @Autowired
    public RegisterHandler(UserService userService) {
        this.userService = userService;
    }

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
