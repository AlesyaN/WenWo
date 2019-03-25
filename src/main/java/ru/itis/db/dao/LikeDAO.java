package ru.itis.db.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.entities.Like;
import ru.itis.entities.Question;
import ru.itis.entities.User;

import java.util.List;

public class LikeDAO implements LikeDAOInterface{
    JdbcTemplate template = new JdbcTemplate(DataSourceSingleton.getDataSource());
    UserDAOInterface userDAO = new UserDAO();
    QuestionDAOInterface questionDAO = new QuestionDAO();

    private RowMapper<Like> likeRowMapper = (resultSet, i) -> new Like(
            resultSet.getInt("id"),
            userDAO.getUserById(resultSet.getInt("user_id")),
            questionDAO.getQuestionById(resultSet.getInt("question_id"))
    );


    @Override
    public void addLike(User user, Question question) {
        template.update("insert into \"like\"(user_id, question_id) values (?, ?)",
                user.getId(), question.getId());
    }

    @Override
    public void deleteLike(int id) {
        template.update("delete from \"like\" where id=?", id);
    }

    @Override
    public Like getLikeById(int id) {
        return template.queryForObject("select * from \"like\" where id=?", likeRowMapper, id);
    }

    @Override
    public List<Like> getAllLikes() {
        return template.query("select * from \"like\"", likeRowMapper);
    }

    @Override
    public List<Like> getLikesByQuestion(Question question) {
        return template.query("select * from \"like\" where question_id=?", likeRowMapper, question.getId());
    }

    @Override
    public List<Like> getLikesByUser(User user) {
        return template.query("select * from \"like\" where user_id=?", likeRowMapper, user.getId());
    }
}
