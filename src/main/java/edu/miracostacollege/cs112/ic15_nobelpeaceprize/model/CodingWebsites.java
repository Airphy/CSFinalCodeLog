package edu.miracostacollege.cs112.ic15_nobelpeaceprize.model;


import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;


public abstract class CodingWebsites implements Serializable, Named, Comparable<CodingWebsites> {
    protected String mExerciseName;
    protected String mUrl;
    protected String mDateAttempted;
    protected boolean mCompleted;

    protected String mSubmission;

    public String getExerciseName() {
        return mExerciseName;
    }

    public void setExerciseName(String name) {
        mExerciseName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = mUrl;
    }

    public String getSubmission() {
        return mSubmission;
    }

    public void setSubmission(String submission) {
        mSubmission = submission;
    }

    @Override
    public int compareTo(CodingWebsites other) {
        int companyComp = this.mExerciseName.compareToIgnoreCase(other.mExerciseName); // Goes through one letter at a time using ASCII , if you care about casing using .compareTo
        if (companyComp != 0)
            return companyComp;

        int dateComp = this.mDateAttempted.compareToIgnoreCase(other.mDateAttempted);
        if (dateComp != 0)
            return dateComp;

        //int compComp = Boolean.compare(this.mCompleted, other.mCompleted);
        // if(compComp != 0)
        //   return compComp;

        return Boolean.compare(this.mCompleted, other.mCompleted);
        // return this.mRequirements.compareToIgnoreCase(other.mRequirements);
    }


//    @Override
//    public int compareTo(CodingWebsites other) {
//        return Comparator.comparing(CodingWebsites::getDisplayName)
//                .thenComparing(CodingWebsites::getExerciseName)
//                .thenComparing(CodingWebsites::getUrl)
//                .compare(this, other);
//    }


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


    public CodingWebsites(String exerciseName, String dateAttempted, boolean completed, String url, String submission) {
        mExerciseName = exerciseName;
        mDateAttempted = dateAttempted;
        mCompleted = completed;
        mUrl = url;
        mSubmission = submission;
    }

}


