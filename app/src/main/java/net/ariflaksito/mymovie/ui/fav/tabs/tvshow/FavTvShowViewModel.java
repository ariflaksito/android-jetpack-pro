package net.ariflaksito.mymovie.ui.fav.tabs.tvshow;

import net.ariflaksito.mymovie.data.source.AppRepository;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
import net.ariflaksito.mymovie.vo.Resource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

public class FavTvShowViewModel extends ViewModel {

    private AppRepository appRepository;

    public FavTvShowViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public LiveData<Resource<PagedList<TvShowEntity>>> getFavTvShows() {
        return appRepository.getFavTvShows();
    }
}
