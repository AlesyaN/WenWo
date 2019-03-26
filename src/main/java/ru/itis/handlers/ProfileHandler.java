package ru.itis.handlers;

import ru.itis.entities.Question;
import ru.itis.entities.User;
import ru.itis.services.QuestionService;
import ru.itis.services.UserService;

import java.util.Scanner;

public class ProfileHandler {
    private QuestionService questionService = new QuestionService();
    private UserService userService = new UserService();


    public void respond(User user) {
        System.out.println("You are in Profile!");
        System.out.println("\nPERSONAL INFO:");
        System.out.println("username: " + user.getLogin());
        System.out.println("name: " + user.getName());
        System.out.println("surname: " + user.getSurname());
        System.out.println("email:" + user.getEmail());
        System.out.println("city: " + user.getCity());
        System.out.println("gender: " + user.getGender());
        System.out.println("date of birth: " + user.getDate_of_birth());

        System.out.println("\nQUESTIONS:");
        for (Question q : questionService.getAllUserQuestions(user)) {
            System.out.println("Q" + q.getId() + ": " + q.getText());
            if (q.getAnswer() == null) {
                System.out.println("no answers");
            } else
                System.out.println("A: " + q.getAnswer());
            System.out.println("by " + q.getSender().getLogin() + " at " + q.getDate());
        }

        if (userService.getCurrentUser() != null) {
            if (userService.getCurrentUser() == user)
                System.out.println("Choose: \n /edit\n /answer\n /delete\n /logout\n /search");
            else System.out.println("Choose:\n /ask\n /logout\n /search\n /profile");
        }


    }

    public void logOut() {
        userService.logOut();
    }

    public void delete() {
        System.out.println();
    }

    public void ask() {

    }

    public void answer() {
    }

    public void edit() {
    }
}
