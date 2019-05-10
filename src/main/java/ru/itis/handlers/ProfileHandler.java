package ru.itis.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.db.Session;
import ru.itis.entities.Question;
import ru.itis.entities.User;
import ru.itis.services.QuestionService;
import ru.itis.services.UserService;

import java.util.List;
import java.util.Scanner;

@Component
public class ProfileHandler implements HandlerInterface {
    private User profile_user;

    private final UserService userService;
    private final QuestionService questionService;
    Scanner sc = new Scanner(System.in);

    @Autowired
    public ProfileHandler(UserService userService, QuestionService questionService) {
        this.userService = userService;
        this.questionService = questionService;
    }

    public String respond(User user) {
        profile_user = user;
        showInfo();
        if (userService.getCurrentUser() != null) {
            suggest_options();
            return readLine(user);
        } else return "/intro";
    }

    private String readLine(User user) {
        while (true) {
            String line = sc.nextLine();
            switch (line) {
                case "/edit":
                    return "/edit";
                case "/answer":
                    answer();
                case "/ask":
                    ask();
                    break;
                case "/deleteQ":
                    deleteQ();
                    break;
                case "/deleteA":
                    deleteA();
                    break;
                case "/search":
                    return "/search";
                case "/profile":
                    return "/profile " + userService.getCurrentUser().getId();
                case "/logout":
                    return logOut();
                case "/chat":
                    if (user.getId() == Session.getSession().getCurrentUser().getId()) {
                        System.out.println("Wrong command");
                        break;
                    } else {
                        return "/chat " + user;
                    }
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }

    public String logOut() {
        userService.logOut();
        return "/intro";
    }

    public void deleteA() {
        if (userService.getCurrentUser().getId() == profile_user.getId()) {
            List<Question> answered = questionService.getUserAnsweredQuestions(profile_user);
            if (answered.size() > 0) {
                System.out.println("Choose question:");
                for (Question q : answered) {
                    System.out.println("Q" + q.getId() + ": " + q.getText() + " Answer: " + q.getAnswer());
                }
                int q_id = Integer.parseInt(sc.nextLine());
                boolean flag = false;
                for (Question q : answered) {
                    if (q.getId() == q_id) flag = true;
                }
                if (!flag)
                    System.out.println("There is no such answer!");
                else {
                    System.out.println("Answer <" + questionService.getQuestionById(q_id).getAnswer() + "> is deleted");
                    questionService.setAnswer(questionService.getQuestionById(q_id), null);
                }
            } else System.out.println("You haven't answered on any question yet");
        } else {
            System.out.println("You can't delete answers here!");
        }
        showInfo();
        suggest_options();
    }

    public void deleteQ() {
        if (userService.getCurrentUser().getId() != profile_user.getId()) {
            List<Question> unanswered = questionService.getUnansweredQuestionsFromSenderToReceiver(
                    userService.getCurrentUser(), profile_user);
            if (unanswered.size() > 0) {
                System.out.println("Choose and enter a number of question you want to delete(you can delete only unanswered questions):");
                for (Question q : unanswered)
                    System.out.println("Q" + q.getId() + ": " + q.getText());
                int q_id = Integer.parseInt(sc.nextLine());
                boolean flag = false;
                for (Question q : unanswered) {
                    if (q.getId() == q_id) flag = true;
                }
                if (!flag)
                    System.out.println("There is no such question!");
                else {
                    System.out.println("Question <" + questionService.getQuestionById(q_id).getText() + "> is deleted");
                    questionService.deleteQuestion(questionService.getQuestionById(q_id));
                }
            } else System.out.println("You can't delete any question");
        } else {
            if (questionService.getAllUserQuestions(profile_user).size() == 0) {
                System.out.println("You don't have questions yet");
            } else {
                System.out.println("Choose and enter a number of question you want to delete:");
                int q_id = Integer.parseInt(sc.nextLine());
                if (questionService.getQuestionById(q_id).getReceiver().getId() == profile_user.getId()) {
                    System.out.println("This question <" + questionService.getQuestionById(q_id).getText() + "> deleted");
                    questionService.deleteQuestion(questionService.getQuestionById(q_id));
                } else System.out.println("There is no such question!");
            }
        }
        showInfo();
        suggest_options();
    }

    public void ask() {
        if (userService.getCurrentUser().getId() == profile_user.getId()) {
            System.out.println("You can't ask yourself!");
        } else {
            System.out.println("Enter your question:");
            String text = sc.nextLine();
            questionService.addQuestion(userService.getCurrentUser(), profile_user, text);
            System.out.println("question added, receiver will answer soon:)");
        }
        showInfo();
        suggest_options();
    }

    public void answer() {
        if (userService.getCurrentUser().getId() != profile_user.getId()) {
            System.out.println("it's not your profile!");
        } else {
            if (questionService.getAllUserQuestions(profile_user).size()==0)
                System.out.println("You don't have questions yet");
            else {
                System.out.println("Enter number of question you want to answer");
                int q_id = Integer.parseInt(sc.nextLine());
                if (questionService.getQuestionById(q_id) == null)
                    System.out.println("There is no such answer!");
                else {
                    System.out.println("This question: " + questionService.getQuestionById(q_id).getText());
                    System.out.println("Enter your answer:");
                    String text = sc.nextLine();
                    questionService.setAnswer(questionService.getQuestionById(q_id), text);
                    System.out.println("answer added");
                }
            }
        }
        suggest_options();
    }

    private void showInfo() {
        System.out.println("You are in Profile!");
        System.out.println("\nPERSONAL INFO:");
        System.out.println("username: " + profile_user.getLogin());
        System.out.println("name: " + profile_user.getName());
        System.out.println("surname: " + profile_user.getSurname());
        System.out.println("email:" + profile_user.getEmail());
        System.out.println("city: " + profile_user.getCity());
        System.out.println("gender: " + profile_user.getGender());
        System.out.println("date of birth: " + profile_user.getDate_of_birth());

        System.out.println("\nQUESTIONS:");
        List<Question> questions = questionService.getAllUserQuestions(profile_user);
        if (questions.size() > 0) {
            for (Question q : questions) {
                System.out.println("Q" + q.getId() + ": " + q.getText());
                if (q.getAnswer() == null) {
                    System.out.println("no answers");
                } else
                    System.out.println("A: " + q.getAnswer());
                System.out.println("by " + q.getSender().getLogin() + " at " + q.getDate());
            }
        } else System.out.println("You don't have questions yet");

    }

    private void suggest_options() {
        if (userService.getCurrentUser().getId() == profile_user.getId()) {
            System.out.println("OPTIONS:");
            System.out.println("/edit");
            System.out.println("/answer");
            System.out.println("/deleteQ");
            System.out.println("/deleteA");
            System.out.println("/search");
            System.out.println("/logout");
        } else {
            System.out.println("OPTIONS:");
            System.out.println("/chat");
            System.out.println("/ask");
            System.out.println("/deleteQ");
            System.out.println("/profile");
            System.out.println("/search");
            System.out.println("/logout");
        }
    }
}
