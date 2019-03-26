package ru.itis;

import ru.itis.db.dao.UserDAO;
import ru.itis.entities.User;
import ru.itis.handlers.*;
import ru.itis.services.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProfileHandler profileHandler = new ProfileHandler();
        LoginHandler loginHandler = new LoginHandler();
        RegisterHandler registerHandler = new RegisterHandler();
        SearchHandler searchHandler = new SearchHandler();
        System.out.println("hi choose one of r-register l-login s-search e-exit");
        String request = scanner.nextLine();
        /*User user = enterHandler.respond(request);
        while (!request.equals("e")) {

            if (user != null) {
                profileHandler.respond(user);
            } else
            System.out.println("\nchoose one of r-register l-login s-search e-exit");
            request = scanner.nextLine();
        }*/
        while (!request.equals("/exit")){
            switch (request) {
                case "/login":
                    loginHandler.respond();
                    break;
                case "/register":
                    registerHandler.respond();
                    break;
                case "/search":
                    searchHandler.respond();
                    break;
                default:
                    System.out.println("error");
                    break;
            }
            request = scanner.nextLine();
        }
    }


}
