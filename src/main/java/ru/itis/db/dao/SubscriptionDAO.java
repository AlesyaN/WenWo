package ru.itis.db.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.entities.Subscription;
import ru.itis.entities.User;

import java.util.List;

public class SubscriptionDAO implements SubscriptionDAOInterface {
    JdbcTemplate template = new JdbcTemplate(DataSourceSingleton.getDataSource());
    UserDAOInterface userDAO = new UserDAO();

    RowMapper<Subscription> subscriptionRowMapper = (resultSet, i) -> new Subscription(
        resultSet.getInt("id"),
        userDAO.getUserById(resultSet.getInt("subscriptor_id")),
        userDAO.getUserById(resultSet.getInt("subscriber_id")));

    @Override
    public List<Subscription> getAllSubscriptions() {
        return template.query("select * from subscriptions", subscriptionRowMapper);
    }

    @Override
    public List<Subscription> getSubscriptorsByUser(User user) {
        return template.query("select * from subscriptions where subscriber_id=?", subscriptionRowMapper, user.getId());
    }

    @Override
    public List<Subscription> getSubscribersByUser(User user) {
        return template.query("select * from subscriptions where subscriptor_id=?", subscriptionRowMapper, user.getId());
    }

    @Override
    public void addSubscription(User subscriptor, User subscriber) {
        template.update("insert into subscriptions(subscriptor_id, subscriber_id) values (?, ?)",
                subscriptor.getId(), subscriber.getId());
    }

    @Override
    public void deleteSubscription(int id) {
        template.update("delete from subscriptions where id=?", id);
    }
}
