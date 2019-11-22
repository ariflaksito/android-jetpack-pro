package net.ariflaksito.mymovie.ui.fav.tabs.movies;

import net.ariflaksito.mymovie.data.source.AppRepository;
import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FavMovieViewModelTest {

    private FavMovieViewModel viewModel;
    private AppRepository appRepository = mock(AppRepository.class);

    @Before
    public void setUp() {
        viewModel = new FavMovieViewModel(appRepository);
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void getFavMovies() {
        MutableLiveData<Resource<PagedList<MovieEntity>>> movies = new MutableLiveData<>();
        when(appRepository.getFavMovies()).thenReturn(movies);
        Observer<Resource<PagedList<MovieEntity>>> observer = mock(Observer.class);

        viewModel.getFavMovies().observeForever(observer);
        assertNotNull(movies);
    }
}