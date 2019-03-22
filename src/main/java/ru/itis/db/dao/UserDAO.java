package ru.itis.db.dao;

import org.apache.commons.codec.digest.DigestUtils;
import ru.itis.db.ConnectionSingleton;
import ru.itis.db.orm.ORM;
import ru.itis.db.orm.ORMInterface;
import ru.itis.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements UserDAOInterface{
    Connection conn = ConnectionSingleton.getConnection();
    ORMInterface orm = new ORM();

    public User getUserById(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from \"user\" where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return orm.makeUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from \"user\"");
            ResultSet rs = ps.executeQuery();
            return orm.makeListOfUsers(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(String login, String email, String password) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into \"user\"(login, email, password) values (?, ?, ?)");
            ps.setString(1, login);
            ps.setString(2, email);
            ps.setString(3, DigestUtils.md5Hex(password));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByLogin(String login) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from \"user\" where login=?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            return orm.makeUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

