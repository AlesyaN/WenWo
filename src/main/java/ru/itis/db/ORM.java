package ru.itis.db;

import ru.itis.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ORM implements ORMInterface{

    public User makeUser(ResultSet rs) {
        try {
            if (!rs.isBeforeFirst()) {
                return null;
            } else {
                rs.next();
                return new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> makeListOfUsers(ResultSet rs) {
        List<User> userList = new ArrayList<User>();
        try {
            while (rs.next()) {
                userList.add(new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
