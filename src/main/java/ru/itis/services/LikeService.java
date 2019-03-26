package ru.itis.services;

import ru.itis.db.dao.LikeDAO;
import ru.itis.entities.Like;
import ru.itis.entities.Question;
import ru.itis.entities.User;

import java.util.List;

public class LikeService {
    private LikeDAO likeDAO = new LikeDAO();

    public void addLike(Like like) {
        likeDAO.addLike(like.getUser(), like.getQuestion());
    }

    public void deleteLike(Like like) {
        likeDAO.deleteLike(like.getId());
    }

    public Like getLikeById(int id) {
        return likeDAO.getLikeById(id);
    }

    public List<Like> getLikesByQuestion(Question question) {
        return likeDAO.getLikesByQuestion(question);
    }

    public List<Like> getLikesByUser(User user) {
        return likeDAO.getLikesByUser(user);
    }
}
