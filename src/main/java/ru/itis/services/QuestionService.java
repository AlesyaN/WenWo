package ru.itis.services;


import ru.itis.db.dao.QuestionDAO;
import ru.itis.entities.Question;
import ru.itis.entities.User;

import java.util.List;

public class QuestionService {
    private QuestionDAO questionDAO = new QuestionDAO();

    public List<Question> getAllUserQuestions(User user){
        return questionDAO.getQuestionsToReceiver(user);
    }
}
