package ru.itis.db.orm;

import ru.itis.entities.User;

import java.sql.ResultSet;
import java.util.List;

public interface ORMInterface {
    User makeUser(ResultSet rs);
    List<User> makeListOfUsers(ResultSet rs);

}