package edu.uga.cs.geoquizzer;

import java.util.ArrayList;

public class Quiz {
    private long id;
    private String date;
    private String currentScore;
    private int numOfAnsweredQuestions;
    private ArrayList<Question> questions;

    public Quiz() {
        this.id = -1;
        this.date = null;
        this.currentScore = null;
        this.questions = null;
        this.numOfAnsweredQuestions = 0;
    }

    public Quiz(String date, String currentScore) {
        this.id = -1;
        this.date = date;
        this.currentScore = currentScore;
        this.numOfAnsweredQuestions = 0;
        this.questions = new ArrayList<>();
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

    public String getCurrentScore() {
        return this.currentScore;
    }

    public void setCurrentScore(String score) {
        this.currentScore = currentScore;
    }

    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String toString() {
        return id + ": " + date + " " + currentScore;
    }
}
