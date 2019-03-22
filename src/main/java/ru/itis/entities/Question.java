package ru.itis.entities;

import java.util.Date;

public class Question {
    private int id;
    private User sender;
    private User receiver;
    private String text;
    private Date date;
    private boolean answered;

    public Question(int id, User sender, User receiver, String text, Date date, boolean answered) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.date = date;
        this.answered = answered;
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

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}
