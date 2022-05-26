package edu.miracostacollege.cs112.ic15_nobelpeaceprize.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public class CodeChef extends CodingWebsites implements Serializable {
    //private String mExerciseName;


    public CodeChef(String exerciseName, String dateAttempted, boolean completed, String url, String submission) {
        super(exerciseName, dateAttempted, completed, url, submission);
    }

    @Override
    public String getDisplayName() {
        return "Code Chef";
    }

    @Override
    public String toString() {
        return "Code Chef [" +
                "Exercise Name=" + mExerciseName +
                ", URL=" + mUrl +
                ", Date Attempted=" + mDateAttempted +
                ", Completed=" + mCompleted +
                ']';
    }
}
