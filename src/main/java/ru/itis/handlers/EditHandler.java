package ru.itis.handlers;

import ru.itis.entities.User;
import ru.itis.services.UserService;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Scanner;

public class EditHandler implements HandlerInterface {
    private Scanner scanner = new Scanner(System.in);
    private UserService userService = new UserService();

    public String respond(){
        System.out.println("choose filed you want to edit: \nusername \npassword \nname \nsurname \nemail \ncity \ngender \ndateOfBirth");
        String field = scanner.nextLine();
        User updated = userService.getCurrentUser();
        if (field.equals("username") || field.equals("password") || field.equals("name") || field.equals("surname") || field.equals("email") || field.equals("city") || field.equals("gender") || field.equals("dateOfBirth")) {
            System.out.println("enter new " + field);
            String value = scanner.nextLine();
            if (field.equals("username") && !userService.loginIsUnique(value))
                System.out.println("this username is already taken");
            else if (field.equals("email") && !userService.emailIsUnique(value))
                System.out.println("this email is already registered");
            else {
                try {
                    Class[] argTypes = new Class[] { String.class };
                    String [] args = {value};
                    User.class.getMethod("set" + firstLetterToUpperCase(field), argTypes).invoke(updated,(String) args[0]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                userService.update(updated);
            }
        } else System.out.println("wrong input");
        return "/profile " + userService.getCurrentUser().getId();
    }

    private String firstLetterToUpperCase(String str) {
        return String.valueOf(Character.toUpperCase(str.charAt(0))) +
                str.substring(1);
    }
}
