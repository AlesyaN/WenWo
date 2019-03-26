package ru.itis.db.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.entities.Message;
import ru.itis.entities.User;

import java.util.List;

public class MessageDAO implements MessageDAOInterface {
    JdbcTemplate template = new JdbcTemplate(DataSourceSingleton.getDataSource());
    UserDAOInterface userDAO = new UserDAO();

    RowMapper<Message> messageRowMapper = (resultSet, i) -> new Message(
            resultSet.getInt("id"),
            userDAO.getUserById(resultSet.getInt("sender_id")),
            userDAO.getUserById(resultSet.getInt("reciever_id")),
            resultSet.getString("message"),
            resultSet.getTimestamp("date")
    );

    @Override
    public Message getMessageById(int id) {
        return template.queryForObject("select * from message where id=?", messageRowMapper, id);
    }

    @Override
    public List<Message> getAllMessages() {
        return template.query("select * from message", messageRowMapper);
    }

    @Override
    public List<Message> getMessagesByUsers(User user1, User user2) {
        return template.query("select * from message where sender_id=? and reciever_id=?" +
                "or sender_id=? and reciever_id=?", messageRowMapper,
                user1.getId(), user2.getId(), user2.getId(), user1.getId());
    }

    @Override
    public void addMessage(User sender, User receiver, String text) {
        template.update("insert into message(sender_id, reciever_id, message, \"date\") " +
                "values (?, ?, ?, now())", sender.getId(), receiver.getId(), text);
    }

    @Override
    public void deleteMessage(int id) {
        template.update("delete from message where id=?", id);
    }
}
