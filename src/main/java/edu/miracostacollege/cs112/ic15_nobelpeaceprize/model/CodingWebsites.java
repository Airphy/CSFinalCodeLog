package edu.miracostacollege.cs112.ic15_nobelpeaceprize.model;


import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public abstract class CodingWebsites implements Serializable {
    protected String mExerciseName;
    protected String mDateAttempted;
    protected boolean mCompleted;

    public String getExerciseName() {
        return mExerciseName;
    }

    public void setExerciseName(String name) {
        mExerciseName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodingWebsites that = (CodingWebsites) o;
        return mCompleted == that.mCompleted && Objects.equals(mExerciseName, that.mExerciseName) && Objects.equals(mDateAttempted, that.mDateAttempted);
    }



    public void setDateAttempted(String date) {
        mDateAttempted = date;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public CodingWebsites(String exerciseName, String dateAttempted, boolean completed) {
        mExerciseName = exerciseName;
        mDateAttempted = dateAttempted;
        mCompleted = completed;
    }
/*
    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return "Log [" +
                "Exercise Name=" + mExerciseName +
                ", Date Attempted=" + mDateAttempted +
                ", Completed=" + mCompleted +
                ']';
    }
    */

}

