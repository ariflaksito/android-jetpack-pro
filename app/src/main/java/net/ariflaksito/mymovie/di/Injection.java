package net.ariflaksito.mymovie.di;

import android.app.Application;

import net.ariflaksito.mymovie.data.source.AppRepository;
import net.ariflaksito.mymovie.data.source.local.LocalRepository;
import net.ariflaksito.mymovie.data.source.local.room.MovieDatabase;
import net.ariflaksito.mymovie.data.source.remote.RemoteRepository;
import net.ariflaksito.mymovie.utils.ApiMovie;
import net.ariflaksito.mymovie.utils.ApiTvShow;
import net.ariflaksito.mymovie.utils.AppExecutors;

public class Injection {
    public static AppRepository provideMovieRepository(Application application) {

        MovieDatabase database = MovieDatabase.getDatabase(application);
        LocalRepository localRepository = LocalRepository.getInstance(database.movieDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new ApiMovie(application));
        AppExecutors appExecutors = new AppExecutors();

        return AppRepository.getInstance(localRepository, remoteRepository, appExecutors);
    }

    public static AppRepository provideTvShowRepository(Application application) {

        MovieDatabase database = MovieDatabase.getDatabase(application);
        LocalRepository localRepository = LocalRepository.getInstance(database.movieDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new ApiTvShow(application));
        AppExecutors appExecutors = new AppExecutors();

        return AppRepository.getInstance(localRepository, remoteRepository, appExecutors);

    }

    public static AppRepository provideFavMovieRepository(Application application){
        MovieDatabase database = MovieDatabase.getDatabase(application);
        LocalRepository localRepository = LocalRepository.getInstance(database.movieDao());
        AppExecutors appExecutors = new AppExecutors();

        return AppRepository.getInstance(localRepository, null, appExecutors);
    }

    public static AppRepository provideFavTvShowRepository(Application application){
        MovieDatabase database = MovieDatabase.getDatabase(application);
        LocalRepository localRepository = LocalRepository.getInstance(database.movieDao());
        AppExecutors appExecutors = new AppExecutors();

        return AppRepository.getInstance(localRepository, null, appExecutors);
    }

    public static AppRepository provideDetailRepository(Application application){

        MovieDatabase database = MovieDatabase.getDatabase(application);
        LocalRepository localRepository = LocalRepository.getInstance(database.movieDao());
        AppExecutors appExecutors = new AppExecutors();

        return AppRepository.getInstance(localRepository, null, appExecutors);
    }
}
