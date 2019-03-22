package ru.itis.db;

import ru.itis.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    Connection conn = ConnectionSingleton.getConnection();
    ORMInterface orm = new ORM();

    public User getUser(int id) {
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
}
