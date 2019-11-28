package com.example.musicplayerproject;

public class MusicItemDTO {
    private String id;
    private String title;
    private String singer;
    private long duration;
    private int countClicked;
    private String albumArt;
    private String path;

    public MusicItemDTO() {
    }

    public MusicItemDTO(String id, String title, String singer, long duration, int countClicked, String albumArt, String path) {
        this.id = id;
        this.title = title;
        this.singer = singer;
        this.duration = duration;
        this.countClicked = countClicked;
        this.albumArt = albumArt;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getCountClicked() {
        return countClicked;
    }

    public void setCountClicked(int countClicked) {
        this.countClicked = countClicked;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
