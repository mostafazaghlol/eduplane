package com.eltamiuz.eduplane.Models;

public class courseModel {
    private String courseTitle,courseTime,courseId;
    private int courseImage;
    public courseModel(String courseTitle,String courseTime,int courseImage,String courseId){
        this.courseTitle = courseTitle;
        this.courseTime = courseTime;
        this.courseImage = courseImage;
        this.courseId = courseId;
    }

    public int getCourseImage() {
        return courseImage;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseId() {
        return courseId;
    }
}
