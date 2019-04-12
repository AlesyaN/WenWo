package ru.itis.db.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.entities.Question;
import ru.itis.entities.User;

import java.sql.ResultSet;
import java.util.List;

public class QuestionDAO implements QuestionDAOInterface {
    JdbcTemplate template = new JdbcTemplate(DataSourceSingleton.getDataSource());
    UserDAOInterface userDAO = new UserDAO();

    RowMapper<Question> questionRowMapper = (ResultSet rs, int i) -> new Question(rs.getInt("id"),
            userDAO.getUserById(rs.getInt("sender_id")),
            userDAO.getUserById(rs.getInt("reciever_id")),
            rs.getString("message"),
            rs.getTimestamp("date"),
            rs.getString("answer"));

    public Question getQuestionById(int id) {
        try {
            return template.queryForObject("select * from question where id=?", questionRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public List<Question> getAllQuestions() {
        return template.query("select * from question", questionRowMapper);
    }

    public List<Question> getQuestionsToReceiver(User receiver) {
        return template.query("select * from question where reciever_id=?", questionRowMapper, receiver.getId());
    }

    public List<Question> getQuestionsFromSenderToReceiver(User sender, User receiver) {
        return template.query("select * from question where reciever_id=? and sender_id=?",
                questionRowMapper, receiver.getId(), sender.getId());
    }

    public List<Question> getAnsweredQuestionsToReceiver(User receiver) {
        return template.query("select * from question where reciever_id=? and answer is not null",
                questionRowMapper, receiver.getId());
    }

    public List<Question> getUnansweredQuestionsToReceiver(User receiver) {
        return template.query("select * from question where reciever_id=? and answer is null",
                questionRowMapper, receiver.getId());
    }

    public List<Question> getAnsweredQuestionsFromSenderToReceiver(User sender, User receiver) {
        return template.query("select * from question where reciever_id=? and sender_id=? and answer is not null",
                questionRowMapper, receiver.getId(), sender.getId());
    }

    public List<Question> getUnansweredQuestionsFromSenderToReceiver(User sender, User receiver) {
        return template.query("select * from question where reciever_id=? and sender_id=? and answer is null",
                questionRowMapper, receiver.getId(), sender.getId());
    }

    @Override
    public void addQuestion(User sender, User receiver, String message) {
        template.update("insert into question(sender_id, reciever_id, message, \"date\") values (?, ?, ?, now())",
                sender.getId(), receiver.getId(), message);
    }


    @Override
    public void setAnswer(Question question, String answer) {
        template.update("update question set answer=? where id=?", answer, question.getId());
    }

    @Override
    public void deleteQuestion(Question question) {
        template.update("delete from question where id=?", question.getId());
    }
}
