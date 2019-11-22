package net.ariflaksito.mymovie.data.source;

import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
import net.ariflaksito.mymovie.vo.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public interface AppDataSource {
    LiveData<Resource<List<MovieEntity>>> getAllMovies();

    LiveData<Resource<List<TvShowEntity>>> getAllTvShows();

    LiveData<Resource<PagedList<MovieEntity>>> getFavMovies();

    LiveData<Resource<PagedList<TvShowEntity>>> getFavTvShows();

    LiveData<Resource<MovieEntity>> getMovie(String movieId);

    LiveData<Resource<TvShowEntity>> getTvShow(String movieId);

    void setFavMovie(MovieEntity movie, boolean state);

    void setFavTvShow(TvShowEntity tvShow, boolean state);

}
