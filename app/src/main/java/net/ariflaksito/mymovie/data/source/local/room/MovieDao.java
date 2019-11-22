package net.ariflaksito.mymovie.data.source.local.room;

import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;

import java.util.List;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MovieDao {

    // Movies
    @WorkerThread
    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntity>> getAllMovies();

    @WorkerThread
    @Query("SELECT * FROM movies WHERE bookmarked = 1")
    LiveData<List<MovieEntity>> getFavMovies();

    @WorkerThread
    @Query("SELECT * FROM movies WHERE id= :movieId")
    LiveData<MovieEntity> getMovie(String movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovies(List<MovieEntity> movies);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateMovie(MovieEntity movie);

    // Tv Shows
    @WorkerThread
    @Query("SELECT * FROM tv_shows")
    LiveData<List<TvShowEntity>> getAllTvShows();

    @WorkerThread
    @Query("SELECT * FROM tv_shows  WHERE bookmarked = 1")
    LiveData<List<TvShowEntity>> getFavTvShows();

    @WorkerThread
    @Query("SELECT * FROM tv_shows WHERE id= :movieId")
    LiveData<TvShowEntity> getTvShow(String movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTvShows(List<TvShowEntity> movies);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateTvShow(TvShowEntity tvShow);

    // Paging
    @Query("SELECT * FROM movies WHERE bookmarked = 1")
    DataSource.Factory<Integer, MovieEntity> getFavMoviesAsPaged();

    @Query("SELECT * FROM tv_shows WHERE bookmarked = 1")
    DataSource.Factory<Integer, TvShowEntity> getFavTvShowsAsPaged();
}
