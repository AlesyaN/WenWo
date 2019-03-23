package ru.itis.db;

import org.apache.commons.codec.digest.DigestUtils;
import ru.itis.db.dao.QuestionDAO;
import ru.itis.db.dao.UserDAO;
import ru.itis.entities.Question;
import ru.itis.entities.User;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
//        User user = userDAO.getUserById(1);
//        System.out.println(user.getId() + " " + user.getLogin() + " " + user.getPassword() + " " + user.getEmail());

//        List<User> users = userDAO.getAllUsers();
//        for (User u: users) {
//            System.out.println(u.toString());
//        }

//        userDAO.addUser("stepan", "stepan@mail.ru", "lepa");
//        User user = userDAO.getUserByLogin("stepan");
//        System.out.println(user.toString());
        QuestionDAO questionDAO = new QuestionDAO();
//        System.out.println(questionDAO.getQuestionById(2).toString());
//        for (Question question: questionDAO.getUnansweredQuestionsFromSenderToReceiver(userDAO.getUserById(1), userDAO.getUserById(2))) {
//            System.out.println(question.toString());
//        }

//        questionDAO.addQuestion(userDAO.getUserById(1), userDAO.getUserById(2), "rowmappers are cool!");
        questionDAO.setAnswer(questionDAO.getQuestionById(7), "yeah!!");
//        questionDAO.deleteQuestion(questionDAO.getQuestionById(6));
        for (Question question : questionDAO.getAllQuestions()) {
            System.out.println(question.toString());
        }




    }
}
