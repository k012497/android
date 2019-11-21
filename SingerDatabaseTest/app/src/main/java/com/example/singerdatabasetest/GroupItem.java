package com.example.singerdatabasetest;

public class GroupItem {
    String name;
    int headCount;

    public GroupItem(String name, int headCount) {
        this.name = name;
        this.headCount = headCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeadCount() {
        return headCount;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }
}
