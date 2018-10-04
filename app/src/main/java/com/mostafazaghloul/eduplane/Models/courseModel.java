package com.mostafazaghloul.eduplane.Models;

public class courseModel {
    private String courseTitle,courseTime;
    private int courseImage;
    public courseModel(String courseTitle,String courseTime,int courseImage){
        this.courseTitle = courseTitle;
        this.courseTime = courseTime;
        this.courseImage = courseImage;
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
}
