package com.example.notepad;

public class Data
{
    private String date,time,title,content;
    private long id;

    public Data()
    {}

    public Data(String date, String time, String title, String content) {
        this.date = date;
        this.time = time;
        this.title = title;
        this.content = content;
    }

    public Data(long id, String date, String time, String title, String content) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
