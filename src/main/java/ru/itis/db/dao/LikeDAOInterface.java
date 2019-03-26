package ru.itis.db.dao;

import ru.itis.entities.Like;
import ru.itis.entities.Question;
import ru.itis.entities.User;

import java.util.List;

public interface LikeDAOInterface {
    void addLike(User user, Question question);
    void deleteLike(int id);
    Like getLikeById(int id);
    List<Like> getAllLikes();
    List<Like> getLikesByQuestion(Question question);
    List<Like> getLikesByUser(User user);
}
