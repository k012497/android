package com.example.musicplayerproject;

public class MusicItemDTO {
    private String title;
    private String singer;
    private String genre;
    private int countClicked;
    private String albumArt;

    public MusicItemDTO(String title, String singer, String genre, int countClicked, String albumArt) {
        this.title = title;
        this.singer = singer;
        this.genre = genre;
        this.countClicked = countClicked;
        this.albumArt = albumArt;
    }

    public String getTitle() {
        return title;
    }

    public String getSinger() {
        return singer;
    }

    public String getGenre() {
        return genre;
    }

    public int getCountClicked() {
        return countClicked;
    }

    public String getAlbumArt() {
        return albumArt;
    }
}
