package com.example.customlistviewtest;

public class ListItemVO {
    private int ImageResId1;
    private int ImageResId2;
    private String stringData1;
    private String stringData2;

    public int getImageResId1() {
        return ImageResId1;
    }

    public void setImageResId1(int imageResId1) {
        ImageResId1 = imageResId1;
    }

    public int getImageResId2() {
        return ImageResId2;
    }

    public void setImageResId2(int imageResId2) {
        ImageResId2 = imageResId2;
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

    public ListItemVO(int imageResId1, int imageResId2, String stringData1, String stringData2) {
        this.ImageResId1 = imageResId1;
        this.ImageResId2 = imageResId2;
        this.stringData1 = stringData1;
        this.stringData2 = stringData2;
    }


}
