package edu.miracostacollege.cs112.ic15_nobelpeaceprize.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public class HackerRank extends CodingWebsites implements Serializable {
  //private String mExerciseName;


  public HackerRank(String exerciseName, String dateAttempted, boolean completed) {
    super(exerciseName, dateAttempted, completed);
  }
}
