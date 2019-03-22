package ru.itis.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Question {
    private int id;
    private User sender;
    private User receiver;
    private String text;
    private Date date;
    private String answer;

    public Question(int id, User sender, User receiver, String text, Date date) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String toString(){
        return id + " " +
                receiver.getLogin() + " " +
                sender.getLogin() + " " +
                text + " " +
                (new SimpleDateFormat("dd-MM-yyyy")).format(date) + " " + answer;
    }
}
