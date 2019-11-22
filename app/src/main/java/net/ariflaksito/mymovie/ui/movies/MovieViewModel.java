package net.ariflaksito.mymovie.ui.movies;

import net.ariflaksito.mymovie.data.source.AppRepository;
import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.vo.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private AppRepository appRepository;

    public MovieViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public LiveData<Resource<List<MovieEntity>>> getMovies() {
        return appRepository.getAllMovies();
    }

}
