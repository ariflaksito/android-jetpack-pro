package net.ariflaksito.mymovie.ui.movies;

import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.data.source.AppRepository;
import net.ariflaksito.mymovie.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {

    private MovieViewModel viewModel;
    private AppRepository appRepository = mock(AppRepository.class);

    @Before
    public void setUp() {
        viewModel = new MovieViewModel(appRepository);
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void getMovies() {
        MutableLiveData<Resource<List<MovieEntity>>> movies = new MutableLiveData<>();
        when(appRepository.getAllMovies()).thenReturn(movies);
        Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);

        viewModel.getMovies().observeForever(observer);
        assertNotNull(movies);

    }

}