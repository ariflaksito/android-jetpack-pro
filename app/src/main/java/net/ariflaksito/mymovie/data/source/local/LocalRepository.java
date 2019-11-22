package net.ariflaksito.mymovie.data.source.local;

import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
import net.ariflaksito.mymovie.data.source.local.room.MovieDao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

public class LocalRepository {

    private final MovieDao movieDao;

    private LocalRepository(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    private static LocalRepository INSTANCE;

    public static LocalRepository getInstance(MovieDao movieDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(movieDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieEntity>> getAllMovies(){
        return movieDao.getAllMovies();
    }

    public DataSource.Factory<Integer, MovieEntity> getFavMovies(){
        return movieDao.getFavMoviesAsPaged();
    }

    public LiveData<MovieEntity> getMovie(String movieId){
        return movieDao.getMovie(movieId);
    }

    public void insertMovies(List<MovieEntity> movies){
        movieDao.insertMovies(movies);
    }

    public LiveData<List<TvShowEntity>> getAllTvShows(){
        return movieDao.getAllTvShows();
    }

    public DataSource.Factory<Integer, TvShowEntity> getFavTvShows(){
        return movieDao.getFavTvShowsAsPaged();
    }

    public LiveData<TvShowEntity> getTvShow(String movieId){
        return movieDao.getTvShow(movieId);
    }

    public void insertTvShows(List<TvShowEntity> tvShowEntities){
        movieDao.insertTvShows(tvShowEntities);
    }

    public void setFavMovie(MovieEntity movie, boolean newState) {
        movie.setBookmarked(newState);
        movieDao.updateMovie(movie);
    }

    public void setFavTvShow(TvShowEntity movie, boolean newState) {
        movie.setBookmarked(newState);
        movieDao.updateTvShow(movie);
    }

}
