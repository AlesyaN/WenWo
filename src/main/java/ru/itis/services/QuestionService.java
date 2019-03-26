package ru.itis.services;


import ru.itis.db.dao.QuestionDAO;
import ru.itis.entities.Question;
import ru.itis.entities.User;

import java.util.List;

public class QuestionService {
    private QuestionDAO questionDAO = new QuestionDAO();

    public Question getQuestionById(int id) {
        return questionDAO.getQuestionById(id);
    }

    public List<Question> getAllUserQuestions(User user){
        return questionDAO.getQuestionsToReceiver(user);
    }

    public List<Question> getUserUnansweredQuestions(User user) {
        return questionDAO.getUnansweredQuestionsToReceiver(user);
    }
    public List<Question> getQuestionsBySender(User sender, User receiver){
        return questionDAO.getQuestionsFromSenderToReceiver(sender, receiver);
    }
    public void addQuestion(Question question) {
        questionDAO.addQuestion(question.getSender(), question.getReceiver(), question.getText());
    }
    public void deleteQuestion(Question question){
        questionDAO.deleteQuestion(question);
    }
    public void setAnswer(Question question, String answer){
        questionDAO.setAnswer(question, answer);
    }
}
