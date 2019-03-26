package ru.itis.db.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.entities.User;

import java.sql.ResultSet;
import java.util.List;

public class UserDAO implements UserDAOInterface{
    JdbcTemplate template = new JdbcTemplate(DataSourceSingleton.getDataSource());

    private RowMapper<User> userRowMapper = (ResultSet rs, int i) -> new User(
            rs.getInt("id"),
            rs.getString("login"),
            rs.getString("password"),
            rs.getString("email")
    );

    public User getUserById(int id) {
        try {
            return template.queryForObject("select * from \"user\" where id=?", userRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> getAllUsers() {
        return template.query("select * from \"user\"", userRowMapper);
    }

    public void addUser(String login, String email, String password) {
        template.update("insert into \"user\"(login, email, password) values (?, ?, ?)", login, email, password);
    }

    public User getUserByLogin(String login) {
        try {
            return template.queryForObject("select * from \"user\" where login=?", userRowMapper, login);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}

