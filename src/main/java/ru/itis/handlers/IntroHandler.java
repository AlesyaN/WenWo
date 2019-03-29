package ru.itis.handlers;

import java.util.Scanner;

public class IntroHandler implements HandlerInterface{
    private Scanner sc = new Scanner(System.in);
    public String respond(){
        System.out.println("OPTIONS:");
        System.out.println("/register");
        System.out.println("/login");
        System.out.println("/search");
        System.out.println("/exit");
        System.out.println("-------------------------------");
        return readLine();
    }
    public String readLine(){
        return sc.nextLine();
    }
}
