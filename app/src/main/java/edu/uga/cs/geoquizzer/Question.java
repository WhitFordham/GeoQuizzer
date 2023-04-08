package edu.uga.cs.geoquizzer;

import android.media.MediaCodec;


/**
 * Question class that defines a question object.
 */
public class Question {
    private String countryName;
    private String correctAnswer;
    private String incorrectAnswer1;
    private String incorrectAnswer2;

    /**
     * Default constructor for a question object.
     */
    public Question() {
        countryName = null;
        correctAnswer = null;
        incorrectAnswer1 = null;
        incorrectAnswer2 = null;
    }

    /**
     * Constructor for the question object
     * @param countryName name of the country
     * @param correctAnswer correct continent for the country
     * @param incorrectAnswer1 first incorrect answer
     * @param incorrectAnswer2 second incorrect answer
     */
    public Question(String countryName, String correctAnswer,
                    String incorrectAnswer1, String incorrectAnswer2) {
        this.countryName = countryName;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer1 = incorrectAnswer1;
        this.incorrectAnswer2 = incorrectAnswer2;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getIncorrectAnswer1() {
        return this.incorrectAnswer1;
    }

    public void setIncorrectAnswer1(String incorrectAnswer1) {
        this.incorrectAnswer1 = incorrectAnswer1;
    }

    public String getIncorrectAnswer2() {
        return this.incorrectAnswer2;
    }

    public void setIncorrectAnswer2(String incorrectAnswer2) {
        this.incorrectAnswer2 = incorrectAnswer2;
    }
}
