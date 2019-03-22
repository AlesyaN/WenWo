package ru.itis.handlers;

import ru.itis.entities.Question;
import ru.itis.entities.User;
import ru.itis.services.QuestionService;
import ru.itis.services.UserService;

import java.util.List;
import java.util.Scanner;

public class EnterHandler {
    private UserService userService = new UserService();
    private QuestionService questionService = new QuestionService();
    Scanner scanner = new Scanner(System.in);
    public User respond(String request){
        if (request.equals("r")){
            return register();
        }
        else if (request.equals("l")){
            return login();
        }
        else if (request.equals("s")){
            return search();
        }
        return null;
    }
    public User search() {
        System.out.println("enter your friend username");
        String searchingLogin = scanner.nextLine();
        return userService.getUserByLogin(searchingLogin);
    }

    public User login() {
        System.out.println("enter login");
        String login = scanner.nextLine();
        System.out.println("enter pass");
        String pass = scanner.nextLine();
        return userService.authenticate(login, pass);

    }

    public User register() {
        System.out.println("enter username");
        String login = scanner.nextLine();
        System.out.println("enter email");
        String email = scanner.nextLine();
        System.out.println("enter password");
        String password = scanner.nextLine();
        return userService.register(login, email, password);
        //if newUser!=null redirect to profile
    }
}
