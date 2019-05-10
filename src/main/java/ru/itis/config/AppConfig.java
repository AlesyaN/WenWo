package ru.itis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.itis.db.dao.*;
import ru.itis.handlers.*;
import ru.itis.services.MessageService;
import ru.itis.services.QuestionService;
import ru.itis.services.UserService;

@Configuration
@ComponentScan(basePackages = "ru.itis")
public class AppConfig {
    @Bean
    public UserDAOInterface userDAO() {
        return new UserDAO();
    }

    @Bean
    public SubscriptionDAOInterface subscriptionDAO() {
        return new SubscriptionDAO();
    }

    @Bean
    public LikeDAOInterface likeDAO() {
        return new LikeDAO();
    }

    @Bean
    public MessageDAOInterface messageDAO() {
        return new MessageDAO();
    }

    @Bean
    public QuestionDAOInterface questionDAO() {
        return new QuestionDAO();
    }

    @Bean
    public UserService userService() {
        return new UserService(userDAO());
    }

    @Bean
    public MessageService messageService() {
        return new MessageService(messageDAO());
    }

    @Bean
    public QuestionService questionService() {
        return new QuestionService(questionDAO());
    }


    @Bean
    public ChatHandler chatHandler() {
        return new ChatHandler(userService(), messageService());
    }

    @Bean
    public EditHandler editHandler() {
        return new EditHandler(userService());
    }

    @Bean
    public LoginHandler loginHandler() {
        return new LoginHandler(userService());
    }

    @Bean
    public ProfileHandler profileHandler() {
        return new ProfileHandler(userService(), questionService());
    }

    @Bean
    public RegisterHandler registerHandler() {
        return new RegisterHandler(userService());
    }

    @Bean
    public SearchHandler searchHandler() {
        return new SearchHandler(userService());
    }

    @Bean
    public IntroHandler introHandler() {
        return new IntroHandler();
    }
}
