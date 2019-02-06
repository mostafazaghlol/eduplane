package com.eltamiuz.eduplane.Models;

public class courseItem {
    private int id,course_id;
    private String name,type,disc,file,created_at,updated_at;

    public courseItem(int id,int course_id,String name,String type,String disc,String file,String created_at,String updated_at){
        this.id = id;
        this.course_id = course_id;
        this.name = name;
        this.type = type;
        this.disc = disc;
        this.file = file;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getName() {
        return name;
    }

    public String getDisc() {
        return disc;
    }

    public String getFile() {
        return file;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getType() {
        return type;
    }
}
