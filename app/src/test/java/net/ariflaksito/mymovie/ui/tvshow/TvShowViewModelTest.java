package net.ariflaksito.mymovie.ui.tvshow;

import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
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

public class TvShowViewModelTest {

    private TvShowViewModel viewModel;
    private AppRepository appRepository = mock(AppRepository.class);

    @Before
    public void setUp() {
        viewModel = new TvShowViewModel(appRepository);
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void getTvShow() {
        MutableLiveData<Resource<List<TvShowEntity>>> tvShows = new MutableLiveData<>();
        when(appRepository.getAllTvShows()).thenReturn(tvShows);
        Observer<Resource<List<TvShowEntity>>> observer = mock(Observer.class);

        viewModel.getTvShow().observeForever(observer);
        assertNotNull(tvShows);

    }
}