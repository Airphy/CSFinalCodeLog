package edu.miracostacollege.cs112.ic15_nobelpeaceprize.model;


import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public abstract class CodingWebsites implements Serializable, Comparable<CodingWebsites> {
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
    public int compareTo(CodingWebsites other) {
        int companyComp =  this.mExerciseName.compareToIgnoreCase(other.mExerciseName); // Goes through one letter at a time using ASCII , if you care about casing using .compareTo
        if(companyComp != 0)
            return companyComp;

        int dateComp =  this.mDateAttempted.compareToIgnoreCase(other.mDateAttempted);
        if(dateComp != 0)
            return dateComp;

        //int compComp = Boolean.compare(this.mCompleted, other.mCompleted);
       // if(compComp != 0)
         //   return compComp;

        return Boolean.compare(this.mCompleted, other.mCompleted);
       // return this.mRequirements.compareToIgnoreCase(other.mRequirements);
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

