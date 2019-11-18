package com.example.recyclerviewtest;

public class MainItem {

    private int profile;
    private String name;
    private String content;

    public MainItem(int profile, String name, String content) {
        this.profile = profile;
        this.name = name;
        this.content = content;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
