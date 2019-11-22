package net.ariflaksito.mymovie.ui.detail;

import android.util.Log;

import net.ariflaksito.mymovie.data.source.AppRepository;
import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
import net.ariflaksito.mymovie.vo.Resource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class DetailViewModel extends ViewModel {

    private AppRepository appRepository;

    private MutableLiveData<String> movieId = new MutableLiveData<>();

    public DetailViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public LiveData<Resource<MovieEntity>> getMovie(String id) {
        return appRepository.getMovie(id);
    }

    public LiveData<Resource<TvShowEntity>> getTvShow(String id) {
        return appRepository.getTvShow(id);
    }

    public void setMovieId(String courseId) {
        this.movieId.setValue(courseId);
    }

    public LiveData<Resource<MovieEntity>> movieResource = Transformations.switchMap(movieId,
            mMovieId -> appRepository.getMovie(mMovieId));

    public LiveData<Resource<TvShowEntity>> tvResource = Transformations.switchMap(movieId,
            mMovieId -> appRepository.getTvShow(mMovieId));

    void setMovieFavorite() {
        if (movieResource.getValue() != null) {
            MovieEntity movieEntity = movieResource.getValue().data;

            final boolean newState = !movieEntity.isBookmarked();
            appRepository.setFavMovie(movieEntity, newState);
        }
    }

    void setTvShowFavorite() {
        if (tvResource.getValue() != null) {
            TvShowEntity tvShowEntity = tvResource.getValue().data;

            final boolean newState = !tvShowEntity.isBookmarked();
            appRepository.setFavTvShow(tvShowEntity, newState);
        }

    }


}
