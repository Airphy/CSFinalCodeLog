package edu.miracostacollege.cs112.ic15_nobelpeaceprize.model;

import java.io.Serializable;

public class HackerRank extends CodingWebsites implements Serializable {
    private String mDifficulty;


    public HackerRank(String exerciseName, String dateAttempted, boolean completed, String url, String submission) {
        super(exerciseName, dateAttempted, completed, url, submission);
    }

    @Override
    public String getDisplayName() {
        return "Hacker Rank";
    }

    @Override
    public String toString() {
        return "Hacker Rank [" +
                "Exercise Name=" + mExerciseName +
                ", URL=" + mUrl +
                ", Date Attempted=" + mDateAttempted +
                ", Completed=" + mCompleted +
                ']';
    }
}
