package edu.uga.cs.geoquizzer;

import androidx.annotation.NonNull;

public class Quiz {
    private long id;
    private String date;
    private String result;

    public Quiz() {
        this.id = -1;
        this.date = null;
        this.result = null;
    }

    public Quiz(String date, String result) {
        this.id = -1;
        this.date = date;
        this.result = result;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String toString() {
        return id + ": " + date + " " + result;
    }
}
