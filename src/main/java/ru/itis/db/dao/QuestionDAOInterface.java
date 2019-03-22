package ru.itis.db.dao;

import ru.itis.entities.Question;
import ru.itis.entities.User;

import java.util.List;

public interface QuestionDAOInterface {
    Question getQuestionById(int id);
    List<Question> getAllQuestions();
    List<Question> getQuestionsToReceiver(User receiver);
    List<Question> getQuestionsFromSenderToReceiver(User sender, User receiver);
    List<Question> getAnsweredQuestionsToReceiver(User receiver);
    List<Question> getUnansweredQuestionsToReceiver (User receiver);
    List<Question> getAnsweredQuestionsFromSenderToReceiver(User sender, User receiver);
    List<Question> getUnansweredQuestionsFromSenderToReceiver(User sender, User receiver);
    void addQuestion(User sender, User receiver, String message);
    void setAnswer(Question question, String answer);
    void deleteQuestion(Question question);
}