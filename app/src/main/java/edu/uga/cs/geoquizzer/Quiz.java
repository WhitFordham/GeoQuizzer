package edu.uga.cs.geoquizzer;

import androidx.annotation.NonNull;

public class Quiz {
    private long id;
    private String date;
    private String score;

    private Question[] questions;

    public Quiz() {
        this.id = -1;
        this.date = null;
        this.score = null;
        this.questions = null;
    }

    public Quiz(String date, String result) {
        this.id = -1;
        this.date = date;
        this.score = result;
        this.questions = new Question[6];
    }

    public long getId() {
        return this.id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String result) {
        this.score = result;
    }

    public Question[] getQuestions() {
        return this.questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public String toString() {
        return id + ": " + date + " " + score;
    }
}
