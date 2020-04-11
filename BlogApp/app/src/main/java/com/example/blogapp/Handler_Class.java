package com.example.blogapp;

//Declare Variable same as Real Time database's Child Name
//Build a constructor
//put Getter and Setter method
//Build an empty Constructor to avoid project crash

public class Handler_Class {
//Declare Variable same as Real Time database's Child Name
    private String title;
    private String description;
    private String image;

//Build an empty Constructor to avoid project crash
    public Handler_Class()
    {
        //reduce crash affect
    }

//Build a constructor
    public Handler_Class(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

//put Getter and Setter method
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}
