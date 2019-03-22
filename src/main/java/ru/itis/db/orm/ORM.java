package ru.itis.db.orm;

import ru.itis.db.dao.UserDAO;
import ru.itis.db.dao.UserDAOInterface;
import ru.itis.entities.Question;
import ru.itis.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ORM {
    private static UserDAOInterface userDAO = new UserDAO();

    public static User makeUser(ResultSet rs) {
        User user = null;
        try {
            if (rs.isBeforeFirst()) {
                rs.next();
                user = new User(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<User> makeListOfUsers(ResultSet rs) {
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

    public static Question makeQuestion(ResultSet rs) {
        Question question = null;
        try {
            if (rs.isBeforeFirst()) {
                rs.next();
                question = new Question(rs.getInt("id"),
                            userDAO.getUserById(rs.getInt("sender_id")),
                            userDAO.getUserById(rs.getInt("reciever_id")),
                            rs.getString("message"),
                            rs.getTimestamp("date"));
                if (rs.getString("answer") != null)  {
                    question.setAnswer(rs.getString("answer"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    public static List<Question> makeListOfQuestions(ResultSet rs) {
        List<Question> questionList = new ArrayList<Question>();
        try {
            while(rs.next()) {
                Question question = new Question(rs.getInt("id"),
                        userDAO.getUserById(rs.getInt("sender_id")),
                        userDAO.getUserById(rs.getInt("reciever_id")),
                        rs.getString("message"),
                        rs.getTimestamp("date"));
                if (rs.getString("answer") != null)  {
                    question.setAnswer(rs.getString("answer"));
                }
                questionList.add(question);
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return questionList;
    }
}
