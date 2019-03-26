package ru.itis.services;

import ru.itis.db.dao.SubscriptionDAO;
import ru.itis.entities.Subscription;
import ru.itis.entities.User;

import java.util.List;

public class SubscriptionService {
    private SubscriptionDAO subscriptionDAO = new SubscriptionDAO();

    public List<Subscription> getSubscriptorsByUser(User user) {
        return subscriptionDAO.getSubscriptorsByUser(user);
    }

    public List<Subscription> getSubscribersByUser(User user) {
        return subscriptionDAO.getSubscribersByUser(user);
    }

    public void addSubscription(Subscription subscription) {
        subscriptionDAO.addSubscription(subscription.getSubscriptor(), subscription.getSubscriber());
    }
    public void deleteSubscription(int id) {
        subscriptionDAO.deleteSubscription(id);
    }

}
