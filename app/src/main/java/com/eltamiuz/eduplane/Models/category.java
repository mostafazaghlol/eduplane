package com.eltamiuz.eduplane.Models;

import java.util.ArrayList;

public class category {
    private int id;
    private String name,disc,start_at,end_at;
    private ArrayList<String> urls;
    public category(int id
    ,String name
    ,String disc
    ,String start_at
    ,String end_at
    ,ArrayList<String> urls ){
        this.id = id;
        this.name = name;
        this.disc = disc;
        this.start_at = start_at;
        this.end_at = end_at;
        this.urls=urls;
    }

    public int getId() {
        return id;
    }

    public String getDisc() {
        return disc;
    }

    public String getEnd_at() {
        return end_at;
    }

    public String getName() {
        return name;
    }

    public String getStart_at() {
        return start_at;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

}
