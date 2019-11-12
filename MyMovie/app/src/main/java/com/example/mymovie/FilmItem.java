package com.example.mymovie;

public class FilmItem {
    String title;
    String dateReleased;
    String bookingRate;
    String rating;
    int posterImage;

    public FilmItem(String title, String dateReleased, String bookingRate, String rating, int posterImage) {
        this.title = title;
        this.dateReleased = dateReleased;
        this.bookingRate = bookingRate;
        this.rating = rating;
        this.posterImage = posterImage;
    }

    public String getTitle() {
        return title;
    }

    public String getDateReleased() {
        return dateReleased;
    }

    public String getBookingRate() {
        return bookingRate;
    }

    public String getRating() {
        return rating;
    }

    public int getPosterImage() {
        return posterImage;
    }
}
