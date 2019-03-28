package ru.itis.handlers;

import ru.itis.entities.Message;
import ru.itis.entities.User;
import ru.itis.services.MessageService;
import ru.itis.services.UserService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChatHandler implements HandlerInterface {
    private User currentUser;
    private User receiver;

    MessageService messageService = new MessageService();
    Scanner sc = new Scanner(System.in);

    public ChatHandler() {
    }

    public String respond(User receiver) {
        this.receiver = receiver;
        currentUser = (new UserService()).getCurrentUser();
        printHeader(receiver.getLogin());
        printMessages();
        printOptions();
        return readLine();
    }

    private String readLine() {
        String line[];
        while (true) {
            System.out.println("-------------------------------");
            line = sc.nextLine().split(" ");
            switch (line[0]) {
                case "/new":
                    newMessage(line);
                    break;
                case "/delete":
                    delete(Integer.parseInt(line[1]));
                    break;
                case "/back":
                    return "/profile " + receiver.getId();
                case "/update":
                    printMessages();
                    break;
                case "/my":
                    return "/profile " + currentUser.getId();
                case "/exit":
                    System.exit(1);
                    break;
                case "/options":
                    printOptions();
                    break;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }

    private void delete(Integer id) {
        boolean flag = messageService.deleteMessage(id, currentUser);
        if (flag) {
            printMessages();
        } else {
            System.out.println("You can't delete other user's message");
        }
    }

    private void newMessage(String[] line) {
        Message message = new Message(currentUser, receiver,
                String.join(" ", Arrays.copyOfRange(line, 1, line.length)));
        messageService.addMessage(message);
        printMessages();
    }

    private void printOptions() {
        System.out.println("-------------------------------");
        System.out.println("OPTIONS:");
        System.out.println("-------------------------------");
        System.out.println("/new *your text here* --send new message");
        System.out.println("/delete *id* --delete message");
        System.out.println("/update --update chat");
        System.out.println("/back --back to " + receiver.getLogin() + "'s profile");
        System.out.println("/my --to your profile");
        System.out.println("/exit --quit program");
        System.out.println("/options --see the list of options");
    }

    private void printHeader(String login) {
        System.out.println("-------------------------------");
        System.out.println("CHAT WITH " + login);
        System.out.println("-------------------------------");
    }

    private void printMessages() {
        List<Message> messages = messageService.getMessagesByUsers(currentUser, receiver);

        SimpleDateFormat format = new SimpleDateFormat("hh:mmm:ss dd/MM/yy");
        for (Message message: messages) {
            System.out.println(message.getId() + " " + format.format(message.getDate()) + " " + message.getSender().getLogin() + ":");
            System.out.println("    " + message.getText());
            System.out.println(" ");
        }
    }
}
