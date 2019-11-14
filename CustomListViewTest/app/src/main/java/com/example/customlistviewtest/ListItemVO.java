package com.example.customlistviewtest;

public class ListItemVO {
    private int ImageResId;
    private String stringData;

    public ListItemVO(int imageResId, String stringData) {
        ImageResId = imageResId;
        this.stringData = stringData;
    }

    public int getImageResId() {
        return ImageResId;
    }

    public void setImageResId(int imageResId) {
        ImageResId = imageResId;
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }
}
