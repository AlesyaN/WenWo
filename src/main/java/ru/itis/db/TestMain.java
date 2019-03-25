package ru.itis.db;

import ru.itis.db.dao.SubscriptionDAO;
import ru.itis.db.dao.UserDAO;
import ru.itis.entities.Subscription;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
        subscriptionDAO.deleteSubscription(1);
        for (Subscription s: subscriptionDAO.getSubscribersByUser(userDAO.getUserById(1))) {
            System.out.println(s.toString());
        }
    }
}
