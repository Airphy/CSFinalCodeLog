package edu.miracostacollege.cs112.ic15_nobelpeaceprize.model;

import java.io.Serializable;

public class LeetCode extends CodingWebsites implements Serializable {


    //private String mExerciseName;
    public LeetCode(String exerciseName, String dateAttempted, boolean completed, String url, String submission) {
        super(exerciseName, dateAttempted, completed, url, submission);
    }

    @Override
    public String getDisplayName() {
        return "Leet Code";
    }

    @Override
    public String toString() {
        return "Leet Code [" +
                "Exercise Name=" + mExerciseName +
                ", URL=" + mUrl +
                ", Date Attempted=" + mDateAttempted +
                ", Completed=" + mCompleted +
                ']';
    }
}