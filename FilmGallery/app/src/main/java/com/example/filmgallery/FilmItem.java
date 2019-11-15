package com.example.filmgallery;

public class FilmItem {
    private int posterId;
    private String title;

    public FilmItem(int posterId, String title) {
        this.posterId = posterId;
        this.title = title;
    }

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
