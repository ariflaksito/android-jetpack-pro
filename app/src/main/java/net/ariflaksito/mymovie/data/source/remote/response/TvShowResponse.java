package net.ariflaksito.mymovie.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShowResponse implements Parcelable {
    private String movieId;
    private String title;
    private String description;
    private String release;
    private String imagePath;

    public TvShowResponse(String movieId, String title, String description, String release, String imagePath) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.movieId);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.release);
        dest.writeString(this.imagePath);
    }

    protected TvShowResponse(Parcel in) {
        this.movieId = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.release = in.readString();
        this.imagePath = in.readString();
    }

    public static final Parcelable.Creator<TvShowResponse> CREATOR = new Parcelable.Creator<TvShowResponse>() {
        @Override
        public TvShowResponse createFromParcel(Parcel source) {
            return new TvShowResponse(source);
        }

        @Override
        public TvShowResponse[] newArray(int size) {
            return new TvShowResponse[size];
        }
    };
}
