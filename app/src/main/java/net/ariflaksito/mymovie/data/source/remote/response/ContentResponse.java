package net.ariflaksito.mymovie.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class ContentResponse implements Parcelable {

    private String movieId;
    private String content;
    private String image;
    private String title;

    public ContentResponse() {
    }

    public ContentResponse(String movieId, String content, String image, String title) {
        this.movieId = movieId;
        this.content = content;
        this.image = image;
        this.title = title;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.movieId);
        dest.writeString(this.content);
        dest.writeString(this.image);
        dest.writeString(this.title);
    }

    protected ContentResponse(Parcel in) {
        this.movieId = in.readString();
        this.content = in.readString();
        this.image = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<ContentResponse> CREATOR = new Parcelable.Creator<ContentResponse>() {
        @Override
        public ContentResponse createFromParcel(Parcel source) {
            return new ContentResponse(source);
        }

        @Override
        public ContentResponse[] newArray(int size) {
            return new ContentResponse[size];
        }
    };
}
