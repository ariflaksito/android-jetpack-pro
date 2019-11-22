package net.ariflaksito.mymovie.ui.fav.tabs.tvshow;

import net.ariflaksito.mymovie.data.source.AppRepository;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
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

public class FavTvShowViewModelTest {

    private FavTvShowViewModel viewModel;
    private AppRepository appRepository = mock(AppRepository.class);

    @Before
    public void setUp() {
        viewModel = new FavTvShowViewModel(appRepository);
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void getFavTvShows() {
        MutableLiveData<Resource<PagedList<TvShowEntity>>> movies = new MutableLiveData<>();
        when(appRepository.getFavTvShows()).thenReturn(movies);
        Observer<Resource<PagedList<TvShowEntity>>> observer = mock(Observer.class);

        viewModel.getFavTvShows().observeForever(observer);
        assertNotNull(movies);
    }
}