package com.example.studentgrades.model;

import androidx.room.PrimaryKey;

import java.util.UUID;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class Grade implements RealmModel {
    @PrimaryKey
    public String id = UUID.randomUUID().toString();
    private String courseName;
    private String courseLetterGrade;

    public Grade(){}

    public Grade(String cournseName,String letter) {
        this.courseName = cournseName;
        this.courseLetterGrade = letter;
    }

    public String getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setName(String courseName) {
        this.courseName= courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLetterGrade() {
        return courseLetterGrade;
    }

    public void setCourseLetterGrade(String courseLetterGrade) {
        this.courseLetterGrade = courseLetterGrade;
    }
}
