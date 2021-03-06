package ru.itis.db.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.entities.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

public class UserDAO implements UserDAOInterface {
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

    public void updateUser(User user) {
        template.update("update \"user\" set login = ?, password=?, name=?, surname=?, email=?, city=?, gender=?, dateofbirth=? WHERE id = " + user.getId() + ";", user.getLogin(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail(), user.getCity(), user.getGender(), user.getDate_of_birth());
    }

    public User getUserByLogin(String login) {
        try {
            return template.queryForObject("select * from \"user\" where login=?", userRowMapper, login);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return template.queryForObject("select * from \"user\" where email=?", userRowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}

