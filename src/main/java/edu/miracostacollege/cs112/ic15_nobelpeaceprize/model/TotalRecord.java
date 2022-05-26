package edu.miracostacollege.cs112.ic15_nobelpeaceprize.model;

public class TotalRecord {
    String mWebsite;

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }

    public int getTotalCodes() {
        return mTotalCodes;
    }

    public void setTotalCodes(int mTotalCodes) {
        this.mTotalCodes = mTotalCodes;
    }

    public int getCompleted() {
        return mCompleted;
    }

    public void setCompleted(int mCompleted) {
        this.mCompleted = mCompleted;
    }

    int mTotalCodes;
    int mCompleted;

    public TotalRecord(String website, int totalCodes, int completed) {
        mWebsite = website;
        mTotalCodes = totalCodes;
        mCompleted = completed;
    }
}
