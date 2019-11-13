package com.example.mymovie;

public class FilmItem {
    int count;

    String title;
    String dateReleased;
    String bookingRate;
    String rating;
    int posterImage;

    public FilmItem(int count, String title, String dateReleased, String bookingRate, String rating, int posterImage) {
        this.count = count;
        this.title = title;
        this.dateReleased = dateReleased;
        this.bookingRate = bookingRate;
        this.rating = rating;
        this.posterImage = posterImage;
    }

    public int getCount() {
        return count;
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
