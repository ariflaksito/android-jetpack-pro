package net.ariflaksito.mymovie.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tv_shows")
public class TvShowEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String movieId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "release")
    private String release;

    @ColumnInfo(name = "image")
    private String imagePath;

    @ColumnInfo(name = "bookmarked")
    private boolean bookmarked = false;

    public TvShowEntity(String movieId, String title, String description, String release, String imagePath) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.release = release;
        this.imagePath = imagePath;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }
}
