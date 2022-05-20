package edu.miracostacollege.cs112.ic15_nobelpeaceprize.model;


import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public abstract class CodingWebsites implements Serializable {
    private String mExerciseName;
    private String mDateAttempted;

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
        return Objects.equals(mExerciseName, that.mExerciseName) && Objects.equals(mDateAttempted, that.mDateAttempted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mExerciseName, mDateAttempted);
    }

    public void setDateAttempted(String date) {
        mDateAttempted = date;
    }


    public CodingWebsites(String name, String date) {
        mExerciseName = name;
        mDateAttempted = date;
    }

    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return "Log [" +
                "Exercise Name=" + mExerciseName +
                ", Date Attempted=" + mDateAttempted +
                ']';
    }
}

