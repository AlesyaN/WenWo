package ru.itis.db.dao;

import ru.itis.db.ConnectionSingleton;
import ru.itis.db.orm.ORM;
import ru.itis.entities.Question;
import ru.itis.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO implements QuestionDAOInterface {
    Connection conn = ConnectionSingleton.getConnection();

    public Question getQuestionById(int id) {
        Question question = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from question where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            question = ORM.makeQuestion(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<Question>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from question");
            ResultSet rs = ps.executeQuery();
            questionList = ORM.makeListOfQuestions(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public List<Question> getQuestionsToReceiver(User receiver) {
        List<Question> questionList = new ArrayList<Question>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from question where reciever_id=?");
            ps.setInt(1, receiver.getId());
            ResultSet rs = ps.executeQuery();
            questionList = ORM.makeListOfQuestions(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public List<Question> getQuestionsFromSenderToReceiver(User sender, User receiver) {
        List<Question> questionList = new ArrayList<Question>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from question where reciever_id=? and sender_id=?");
            ps.setInt(1, receiver.getId());
            ps.setInt(2, sender.getId());
            ResultSet rs = ps.executeQuery();
            questionList = ORM.makeListOfQuestions(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public List<Question> getAnsweredQuestionsToReceiver(User receiver) {
        List<Question> questionList = new ArrayList<Question>();
        try {
            PreparedStatement ps =
                    conn.prepareStatement("select * from question where reciever_id=? and answer is not null");
            ps.setInt(1, receiver.getId());
            ResultSet rs = ps.executeQuery();
            questionList = ORM.makeListOfQuestions(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public List<Question> getUnansweredQuestionsToReceiver(User receiver) {
        List<Question> questionList = new ArrayList<Question>();
        try {
            PreparedStatement ps =
                    conn.prepareStatement("select * from question where reciever_id=? and answer is null");
            ps.setInt(1, receiver.getId());
            ResultSet rs = ps.executeQuery();
            questionList = ORM.makeListOfQuestions(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public List<Question> getAnsweredQuestionsFromSenderToReceiver(User sender, User receiver) {
        List<Question> questionList = new ArrayList<Question>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from question where reciever_id=? and sender_id=? and answer is not null");
            ps.setInt(1, receiver.getId());
            ps.setInt(2, sender.getId());
            ResultSet rs = ps.executeQuery();
            questionList = ORM.makeListOfQuestions(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public List<Question> getUnansweredQuestionsFromSenderToReceiver(User sender, User receiver) {
        List<Question> questionList = new ArrayList<Question>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from question where reciever_id=? and sender_id=? and answer is null");
            ps.setInt(1, receiver.getId());
            ps.setInt(2, sender.getId());
            ResultSet rs = ps.executeQuery();
            questionList = ORM.makeListOfQuestions(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    @Override
    public void addQuestion(User sender, User receiver, String message) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into question(sender_id, reciever_id, message, \"date\") values " +
                                                            "(?, ?, ?, now())");
            ps.setInt(1, sender.getId());
            ps.setInt(2, receiver.getId());
            ps.setString(3, message);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setAnswer(Question question, String answer) {
        try {
            PreparedStatement ps = conn.prepareStatement("update question set answer=? where id=?");
            ps.setString(1, answer);
            ps.setInt(2, question.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteQuestion(Question question) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from question where id=?");
            ps.setInt(1, question.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
