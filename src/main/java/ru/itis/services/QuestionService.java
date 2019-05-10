package ru.itis.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.db.dao.QuestionDAO;
import ru.itis.db.dao.QuestionDAOInterface;
import ru.itis.entities.Question;
import ru.itis.entities.User;

import java.util.List;

@Component
public class QuestionService {

    private final QuestionDAOInterface questionDAO;

    @Autowired
    public QuestionService(QuestionDAOInterface questionDAO) {
        this.questionDAO = questionDAO;
    }

    public Question getQuestionById(int id) {
        return questionDAO.getQuestionById(id);
    }

    public List<Question> getAllUserQuestions(User user){
        return questionDAO.getQuestionsToReceiver(user);
    }

    public List<Question> getUserUnansweredQuestions(User user) {
        return questionDAO.getUnansweredQuestionsToReceiver(user);
    }
    public List<Question> getUnansweredQuestionsFromSenderToReceiver(User sender, User receiver){
        return questionDAO.getUnansweredQuestionsFromSenderToReceiver(sender, receiver);
    }
    public List<Question> getUserAnsweredQuestions(User receiver) {
        return questionDAO.getAnsweredQuestionsToReceiver(receiver);
    }
    public List<Question> getQuestionsBySender(User sender, User receiver){
        return questionDAO.getQuestionsFromSenderToReceiver(sender, receiver);
    }
    public void addQuestion(User sender, User receiver, String text) {
        questionDAO.addQuestion(sender, receiver, text);
    }
    public void deleteQuestion(Question question){
        questionDAO.deleteQuestion(question);
    }
    public void setAnswer(Question question, String answer){
        questionDAO.setAnswer(question, answer);
    }
}
