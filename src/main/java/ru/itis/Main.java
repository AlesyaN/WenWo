package ru.itis;

import ru.itis.entities.User;
import ru.itis.handlers.EnterHandler;
import ru.itis.handlers.ProfileHandler;
import ru.itis.services.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EnterHandler enterHandler = new EnterHandler();
        UserService userService = new UserService();
        ProfileHandler profileHandler = new ProfileHandler();
        System.out.println("hi choose one of r-register l-login s-search");
        String request = scanner.nextLine();
        while (!request.equals("exit")) {
            User user = enterHandler.respond(request);
            if (user != null) {
                profileHandler.respond(user);
                if (userService.getCurrentUser() == null)
                    System.out.println("\nchoose one of r-register l-login s-search");
            } else System.out.println("error");
            request = scanner.nextLine();
        }
    }


}