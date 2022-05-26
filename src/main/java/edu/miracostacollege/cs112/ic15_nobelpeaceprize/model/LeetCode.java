package edu.miracostacollege.cs112.ic15_nobelpeaceprize.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public class LeetCode extends CodingWebsites implements Serializable {
    //private String mExerciseName;


    public LeetCode(String exerciseName, String dateAttempted, boolean completed) {
        super(exerciseName, dateAttempted, completed);
    }

    @Override
    public String toString() {
        return "Leet Code [" +
                "Exercise Name=" + mExerciseName +
                ", Date Attempted=" + mDateAttempted +
                ", Completed=" + mCompleted +
                ']';
    }
}