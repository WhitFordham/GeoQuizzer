package edu.uga.cs.geoquizzer;

import java.util.ArrayList;


/**
 * Class for creating a quiz object.
 */
public class Quiz {
    private long id;
    private String date;
    private int currentScore;
    private int numOfAnsweredQuestions;
    private ArrayList<Question> questions;

    /**
     * Initializes a quiz with no parameters.
     */
    public Quiz() {
        this.id = -1;
        this.date = null;
        this.currentScore = 0;
        this.questions = null;
        this.numOfAnsweredQuestions = 0;
    }

    /**
     * Initializes a quiz.
     * @param date date for new quiz object.
     * @param currentScore score for new quiz object.
     */
    public Quiz(String date, int currentScore) {
        this.id = -1;
        this.date = date;
        this.currentScore = currentScore;
        this.numOfAnsweredQuestions = 0;
        this.questions = new ArrayList<>();
        this.numOfAnsweredQuestions = 0;
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

    public int getCurrentScore() {
        return this.currentScore;
    }

    public void setCurrentScore(int score) {
        this.currentScore = score;
    }

    public void incrementScore() { ++this.currentScore; }

    public void decrementScore() { --this.currentScore; }

    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getNumOfAnsweredQuestions() {
        return this.numOfAnsweredQuestions;
    }

    public void setNumOfAnsweredQuestions(int numOfAnsweredQuestions) {
        this.numOfAnsweredQuestions = numOfAnsweredQuestions;
    }

    public String toString() {
        return id + ": " + date + " " + currentScore;
    }
}
