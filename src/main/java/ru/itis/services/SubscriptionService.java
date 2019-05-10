package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.db.dao.SubscriptionDAO;
import ru.itis.db.dao.SubscriptionDAOInterface;
import ru.itis.entities.Subscription;
import ru.itis.entities.User;

import java.util.List;

@Component
public class SubscriptionService {

    private final SubscriptionDAOInterface subscriptionDAO;

    @Autowired
    public SubscriptionService(SubscriptionDAOInterface subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }

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
