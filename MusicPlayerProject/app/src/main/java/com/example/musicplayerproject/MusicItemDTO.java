package com.example.musicplayerproject;

public class MusicItemDTO {
    private int songNo;
    private String title;
    private String singer;
    private String genre;
    private int countClicked;
    private int imageSource;

    public MusicItemDTO(int songNo, String title, String singer, String genre, int countClicked, int imageSource) {
        this.title = title;
        this.singer = singer;
        this.genre = genre;
        this.countClicked = countClicked;
        this.imageSource = imageSource;
    }

    public int getSongNo() {
        return songNo;
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

    public int getImageSource() {
        return imageSource;
    }
}
