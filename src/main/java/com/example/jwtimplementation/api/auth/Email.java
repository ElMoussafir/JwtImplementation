package com.example.jwtimplementation.api.auth;

public class Email {

    private final String to;
    private final String text;
    private final String subject;

    public Email(String to, String text, String subject) {
        this.to = to;
        this.text = text;
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    public String getSubject() {
        return subject;
    }
}
