package ru.itis.db.dao;

import ru.itis.entities.Subscription;
import ru.itis.entities.User;

import java.util.List;

public interface SubscriptionDAOInterface {
    List<Subscription> getAllSubscriptions();
    List<Subscription> getSubscriptorsByUser(User user);
    List<Subscription> getSubscribersByUser(User user);
    void addSubscription(User subscriptor, User subscriber);
    void deleteSubscription(int id);
}
