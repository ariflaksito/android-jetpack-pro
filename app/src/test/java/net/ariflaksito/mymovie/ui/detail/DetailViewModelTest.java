package net.ariflaksito.mymovie.ui.detail;

import net.ariflaksito.mymovie.data.source.AppRepository;
import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
import net.ariflaksito.mymovie.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailViewModelTest {

    private DetailViewModel viewModel;
    private MovieEntity dummyMovieEntity;
    private TvShowEntity dummyTvShowEntity;
    private AppRepository appRepository = mock(AppRepository.class);
    private String dummyMovieId;
    private String dummyTvShowId;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        viewModel = new DetailViewModel(appRepository);

        dummyMovieEntity = new MovieEntity("475557", "Joker", "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.","2019-10-02", "udDclJoHjfjb8Ekgsd4FDteOkCU.jpg");
        dummyTvShowEntity = new TvShowEntity("1412", "Arrow", "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.", "2012-10-10", "gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg");
        dummyMovieId = dummyMovieEntity.getMovieId();
        dummyTvShowId = dummyTvShowEntity.getMovieId();
    }

    @Test
    public void getMovie() {
        Resource<MovieEntity> resource = Resource.success(dummyMovieEntity);
        MutableLiveData<Resource<MovieEntity>> content = new MutableLiveData<>();
        content.setValue(resource);

        when(appRepository.getMovie(dummyMovieId)).thenReturn(content);

        Observer<Resource<MovieEntity>> observer = mock(Observer.class);

        viewModel.getMovie(dummyMovieId).observeForever(observer);
        verify(observer).onChanged(resource);
    }

    @Test
    public void getTvShow() {
        Resource<TvShowEntity> resource = Resource.success(dummyTvShowEntity);
        MutableLiveData<Resource<TvShowEntity>> content = new MutableLiveData<>();
        content.setValue(resource);

        when(appRepository.getTvShow(dummyTvShowId)).thenReturn(content);

        Observer<Resource<TvShowEntity>> observer = mock(Observer.class);

        viewModel.getTvShow(dummyTvShowId).observeForever(observer);
        verify(observer).onChanged(resource);

    }
}