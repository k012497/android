package com.example.customlistviewtest;

public class ListItemVO {
    private int ImageResId;
    private String stringData1;
    private String stringData2;

    public ListItemVO(int imageResId, String stringData1, String stringData2) {
        ImageResId = imageResId;
        this.stringData1 = stringData1;
        this.stringData2 = stringData2;
    }

    public int getImageResId() {
        return ImageResId;
    }

    public void setImageResId(int imageResId) {
        ImageResId = imageResId;
    }

    public String getStringData1() {
        return stringData1;
    }

    public void setStringData1(String stringData1) {
        this.stringData1 = stringData1;
    }

    public String getStringData2() {
        return stringData2;
    }

    public void setStringData2(String stringData2) {
        this.stringData2 = stringData2;
    }
}
