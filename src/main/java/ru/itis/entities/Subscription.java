package ru.itis.entities;

public class Subscription {
    private int id;
    private User subscriptor;
    private User subscriber;

    public Subscription(int id, User subscriptor, User subscriber) {
        this.id = id;
        this.subscriptor = subscriptor;
        this.subscriber = subscriber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSubscriptor() {
        return subscriptor;
    }

    public void setSubscriptor(User subscriptor) {
        this.subscriptor = subscriptor;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }
}
