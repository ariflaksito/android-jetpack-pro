package net.ariflaksito.mymovie.ui.fav.tabs.movies;

import net.ariflaksito.mymovie.data.source.AppRepository;
import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.vo.Resource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

public class FavMovieViewModel extends ViewModel {

    private AppRepository appRepository;

    public FavMovieViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public LiveData<Resource<PagedList<MovieEntity>>> getFavMovies() {
        return appRepository.getFavMovies();
    }
}
