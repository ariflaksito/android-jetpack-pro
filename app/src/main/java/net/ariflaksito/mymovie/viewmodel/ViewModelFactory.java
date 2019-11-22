package net.ariflaksito.mymovie.viewmodel;

import android.app.Application;

import net.ariflaksito.mymovie.data.source.AppRepository;
import net.ariflaksito.mymovie.di.Injection;
import net.ariflaksito.mymovie.ui.detail.DetailViewModel;
import net.ariflaksito.mymovie.ui.fav.tabs.movies.FavMovieViewModel;
import net.ariflaksito.mymovie.ui.fav.tabs.tvshow.FavTvShowViewModel;
import net.ariflaksito.mymovie.ui.movies.MovieViewModel;
import net.ariflaksito.mymovie.ui.tvshow.TvShowViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;
    private final AppRepository appRepository;

    public ViewModelFactory(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public static ViewModelFactory getMovieInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideMovieRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    public static ViewModelFactory getTvShowInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideTvShowRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    public static ViewModelFactory getDetailInstance(Application application){
        if(INSTANCE == null){
            synchronized (ViewModelFactory.class){
                if(INSTANCE == null){
                    INSTANCE = new ViewModelFactory(Injection.provideDetailRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    public static ViewModelFactory getFavTvShowInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideFavTvShowRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    public static ViewModelFactory getFavMovieInstance(Application application){
        if(INSTANCE == null){
            synchronized (ViewModelFactory.class){
                if(INSTANCE == null){
                    INSTANCE = new ViewModelFactory(Injection.provideFavMovieRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieViewModel(appRepository);
        } else if (modelClass.isAssignableFrom(TvShowViewModel.class)) {
            //noinspection unchecked
            return (T) new TvShowViewModel(appRepository);
        } else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailViewModel(appRepository);
        } else if(modelClass.isAssignableFrom(FavMovieViewModel.class)){
            return  (T) new FavMovieViewModel(appRepository);
        } else if(modelClass.isAssignableFrom(FavTvShowViewModel.class)){
            return (T) new FavTvShowViewModel(appRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
