package net.ariflaksito.mymovie.ui.tvshow;

import net.ariflaksito.mymovie.data.source.AppRepository;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
import net.ariflaksito.mymovie.vo.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class TvShowViewModel extends ViewModel {

    private AppRepository appRepository;

    public TvShowViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public LiveData<Resource<List<TvShowEntity>>> getTvShow() {
        return appRepository.getAllTvShows();
    }
}
