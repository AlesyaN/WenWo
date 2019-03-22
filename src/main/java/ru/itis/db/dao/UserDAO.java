package ru.itis.db.dao;

import org.apache.commons.codec.digest.DigestUtils;
import ru.itis.db.ConnectionSingleton;
import ru.itis.db.orm.ORM;
import ru.itis.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDAOInterface{
    Connection conn = ConnectionSingleton.getConnection();

    public User getUserById(int id) {
        User user = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from \"user\" where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            user = ORM.makeUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from \"user\"");
            ResultSet rs = ps.executeQuery();
            userList = ORM.makeListOfUsers(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void addUser(String login, String email, String password) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into \"user\"(login, email, password) values (?, ?, ?)");
            ps.setString(1, login);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByLogin(String login) {
        User user = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from \"user\" where login=?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            user = ORM.makeUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}

