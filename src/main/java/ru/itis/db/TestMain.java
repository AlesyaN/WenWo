package ru.itis.db;

import org.apache.commons.codec.digest.DigestUtils;
import ru.itis.db.dao.QuestionDAO;
import ru.itis.db.dao.UserDAO;
import ru.itis.entities.Question;
import ru.itis.entities.User;

public class TestMain {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
//        User user = userDAO.getUser(1);
//        System.out.println(user.getId() + " " + user.getLogin() + " " + user.getPassword() + " " + user.getEmail());

//        List<User> users = userDAO.getAllUsers();
//        for (User u: users) {
//            System.out.println(u.toString());
//        }

//        userDAO.addUser("peter", "peter@mail.ru", "hello");
//        User user = userDAO.getUserByLogin("peter");
//        System.out.println(user.toString());
        QuestionDAO questionDAO = new QuestionDAO();

//        for (Question question: questionDAO.getUnansweredQuestionsFromSenderToReceiver(userDAO.getUserById(2), userDAO.getUserById(3))) {
//            System.out.println(question.toString());
//        }

//        questionDAO.addQuestion(userDAO.getUserById(1), userDAO.getUserById(2), "this is question from java!");
//        questionDAO.setAnswer(questionDAO.getQuestionById(5), "great!!");
//        questionDAO.deleteQuestion(questionDAO.getQuestionById(4));
        for (Question question : questionDAO.getAllQuestions()) {
            System.out.println(question.toString());
        }




    }
}
