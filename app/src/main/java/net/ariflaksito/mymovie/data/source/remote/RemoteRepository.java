package net.ariflaksito.mymovie.data.source.remote;

import android.os.Handler;

import net.ariflaksito.mymovie.data.source.remote.response.MovieResponse;
import net.ariflaksito.mymovie.data.source.remote.response.TvShowResponse;
import net.ariflaksito.mymovie.utils.ApiMovie;
import net.ariflaksito.mymovie.utils.ApiTvShow;
import net.ariflaksito.mymovie.utils.EspressoIdlingResource;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private ApiMovie apiMovie;
    private ApiTvShow apiTvShow;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    public RemoteRepository(ApiMovie apiMovie) {
        this.apiMovie = apiMovie;
    }

    public RemoteRepository(ApiTvShow apiTvShow) {
        this.apiTvShow = apiTvShow;
    }

    public static RemoteRepository getInstance(ApiMovie apiMovie) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(apiMovie);
        }
        return INSTANCE;
    }

    public static RemoteRepository getInstance(ApiTvShow apiTvShow) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(apiTvShow);
        }
        return INSTANCE;
    }


    public LiveData<ApiResponse<List<MovieResponse>>> getAllMoviesAsLiveData(){
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<List<MovieResponse>>> resultMovie = new MutableLiveData<>();
        Handler handler = new Handler();
        handler.postDelayed(() -> {

            try {
                resultMovie.setValue(ApiResponse.success( new ApiMovie().execute().get()));
                EspressoIdlingResource.decrement();

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, SERVICE_LATENCY_IN_MILLIS);

        return resultMovie;
    }

    public LiveData<ApiResponse<List<TvShowResponse>>> getAllTvShowsAsLiveData(){
        EspressoIdlingResource.increment();

        MutableLiveData<ApiResponse<List<TvShowResponse>>> resultMovie = new MutableLiveData<>();
        Handler handler = new Handler();
        handler.postDelayed(() -> {

            try {
                resultMovie.setValue(ApiResponse.success(new ApiTvShow().execute().get()));
                EspressoIdlingResource.decrement();

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, SERVICE_LATENCY_IN_MILLIS);

        return resultMovie;
    }

}
