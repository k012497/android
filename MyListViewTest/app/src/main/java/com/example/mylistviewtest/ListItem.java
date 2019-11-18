package com.example.mylistviewtest;

public class ListItem {
    String country;
    String capital;
    int imageSouce;

    public ListItem(String country, String capital, int imageSouce) {
        this.country = country;
        this.capital = capital;
        this.imageSouce = imageSouce;
    }

    public String getTitle() {
        return country;
    }

    public void setTitle(String title) {
        this.country = title;
    }

    public String getContent() {
        return capital;
    }

    public void setContent(String content) {
        this.capital = content;
    }

    public int getImageSouce() {
        return imageSouce;
    }

    public void setImageSouce(int imageSouce) {
        this.imageSouce = imageSouce;
    }
}
