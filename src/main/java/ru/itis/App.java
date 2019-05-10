package ru.itis;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.config.AppConfig;
import ru.itis.handlers.*;
import ru.itis.services.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Handler;

public class App {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    Map<Handlers, HandlerInterface> handlersMap;
    private static App app;
    enum Handlers {
        CHAT,
        LOGIN,
        PROFILE,
        REGISTER,
        SEARCH,
        INTRO,
        EDIT
    }

    Scanner sc;
    UserService userService;

    private App(){
        this.handlersMap = new HashMap<>();
        handlersMap.put(Handlers.CHAT, context.getBean(ChatHandler.class));
        handlersMap.put(Handlers.LOGIN, context.getBean(LoginHandler.class));
        handlersMap.put(Handlers.PROFILE, context.getBean(ProfileHandler.class));
        handlersMap.put(Handlers.REGISTER, context.getBean(RegisterHandler.class));
        handlersMap.put(Handlers.SEARCH, context.getBean(SearchHandler.class));
        handlersMap.put(Handlers.INTRO, context.getBean(IntroHandler.class));
        handlersMap.put(Handlers.EDIT, context.getBean(EditHandler.class));

        sc = new Scanner(System.in);
        userService = context.getBean(UserService.class);
    }

    public static App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }

    public void run() {
        System.out.println("-------------------------------");
        System.out.println("WELCOME TO WEN WO");
        System.out.println("-------------------------------");
        System.out.println("OPTIONS:");
        System.out.println("/register");
        System.out.println("/login");
        System.out.println("/search");
        System.out.println("/exit");
        System.out.println("-------------------------------");
        while (true) {
            go(sc.nextLine());
        }

    }

    public void go(String line) {
        String [] comm = line.split(" ");
        switch (comm[0]) {
            case "/intro":
                go(((IntroHandler)handlersMap.get(Handlers.INTRO)).respond());
            case "/edit":
                go(((EditHandler)handlersMap.get(Handlers.EDIT)).respond());
            case "/profile":
                go(((ProfileHandler) handlersMap.get(Handlers.PROFILE)).respond(userService.getUserById(Integer.parseInt(comm[1]))));
            case "/register":
                go(((RegisterHandler) handlersMap.get(Handlers.REGISTER)).respond());
            case "/login":
                go(((LoginHandler) handlersMap.get(Handlers.LOGIN)).respond());
            case "/search":
                go(((SearchHandler) handlersMap.get(Handlers.SEARCH)).respond());
            case "/chat":
                go(((ChatHandler) handlersMap.get(Handlers.CHAT)).respond(userService.getUserById(Integer.parseInt(comm[1]))));
            case "/exit":
                System.exit(1);
            default:
                System.out.println("Wrong command");
        }
    }





}
