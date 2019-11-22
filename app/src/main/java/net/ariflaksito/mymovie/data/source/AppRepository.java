package net.ariflaksito.mymovie.data.source;

import net.ariflaksito.mymovie.data.source.local.LocalRepository;
import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
import net.ariflaksito.mymovie.data.source.remote.ApiResponse;
import net.ariflaksito.mymovie.data.source.remote.RemoteRepository;
import net.ariflaksito.mymovie.data.source.remote.response.MovieResponse;
import net.ariflaksito.mymovie.data.source.remote.response.TvShowResponse;
import net.ariflaksito.mymovie.utils.AppExecutors;
import net.ariflaksito.mymovie.vo.Resource;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class AppRepository implements AppDataSource {

    private volatile static AppRepository INSTANCE = null;

    private final RemoteRepository remoteRepository;
    private final LocalRepository localRepository;
    private final AppExecutors appExecutors;
    private final int numList = 10;


    private AppRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository, AppExecutors appExecutors) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
    }

    public static AppRepository getInstance(LocalRepository localRepository, RemoteRepository remoteData, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (AppRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppRepository(localRepository, remoteData, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getAllMovies() {

        return new NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors){

            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return remoteRepository.getAllMoviesAsLiveData();
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {
                List<MovieEntity> movieEntities = new ArrayList<>();
                for(MovieResponse mov: data){
                    movieEntities.add(new MovieEntity(mov.getMovieId(),
                            mov.getTitle(),
                            mov.getDescription(),
                            mov.getRelease(),
                            mov.getImagePath()));
                }
                localRepository.insertMovies(movieEntities);
            }
        }.asLiveData();

    }

    @Override
    public LiveData<Resource<List<TvShowEntity>>> getAllTvShows() {
        return new NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors){

            @Override
            protected LiveData<List<TvShowEntity>> loadFromDB() {
                return localRepository.getAllTvShows();
            }

            @Override
            protected Boolean shouldFetch(List<TvShowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TvShowResponse>>> createCall() {
                return remoteRepository.getAllTvShowsAsLiveData();
            }

            @Override
            protected void saveCallResult(List<TvShowResponse> data) {
                List<TvShowEntity> tvShowEntities = new ArrayList<>();
                for(TvShowResponse mov: data){
                    tvShowEntities.add(new TvShowEntity(mov.getMovieId(),
                            mov.getTitle(),
                            mov.getDescription(),
                            mov.getRelease(),
                            mov.getImagePath()));
                }
                localRepository.insertTvShows(tvShowEntities);
            }
        }.asLiveData();

    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getFavMovies() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors){

            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavMovies(), numList).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvShowEntity>>> getFavTvShows() {
        return new NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors){

            @Override
            protected LiveData<PagedList<TvShowEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavTvShows(), numList).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvShowEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TvShowResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TvShowResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getMovie(String movieId) {
        return new NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors){

            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getMovie(movieId);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(MovieResponse data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvShowEntity>> getTvShow(String movieId) {
        return new NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors){

            @Override
            protected LiveData<TvShowEntity> loadFromDB() {
                return localRepository.getTvShow(movieId);
            }

            @Override
            protected Boolean shouldFetch(TvShowEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<TvShowResponse>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(TvShowResponse data) {

            }
        }.asLiveData();


    }

    @Override
    public void setFavMovie(MovieEntity movie, boolean state) {
        Runnable runnable = () -> localRepository.setFavMovie(movie, state);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void setFavTvShow(TvShowEntity tvShow, boolean state) {
        Runnable runnable = () -> localRepository.setFavTvShow(tvShow, state);
        appExecutors.diskIO().execute(runnable);
    }

}
