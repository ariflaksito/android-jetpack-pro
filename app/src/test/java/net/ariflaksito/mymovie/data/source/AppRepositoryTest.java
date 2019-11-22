package net.ariflaksito.mymovie.data.source;

import net.ariflaksito.mymovie.data.source.local.LocalRepository;
import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
import net.ariflaksito.mymovie.data.source.remote.RemoteRepository;
import net.ariflaksito.mymovie.data.source.remote.response.ContentResponse;
import net.ariflaksito.mymovie.utils.InstantAppExecutors;
import net.ariflaksito.mymovie.utils.LiveDataTestUtil;
import net.ariflaksito.mymovie.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AppRepositoryTest {

    private LocalRepository local = mock(LocalRepository.class);
    private RemoteRepository remote = mock(RemoteRepository.class);
    private InstantAppExecutors instantAppExecutors = mock(InstantAppExecutors.class);
    private FakeAppRepository appRepository = new FakeAppRepository(local, remote, instantAppExecutors);

    private MovieEntity dummyContentMovie;
    private TvShowEntity dummyContentTvShow;
    private ContentResponse dummyResponseMovie;
    private ContentResponse dummyResponseTvShow;
    private String movieId;
    private String tvShowId;

    @Before
    public void setUp() {
        dummyContentMovie = new MovieEntity("475557", "Joker", "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.", "", "udDclJoHjfjb8Ekgsd4FDteOkCU.jpg");
        dummyContentTvShow = new TvShowEntity("1412", "Arrow", "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.", "", "gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg");

        dummyResponseMovie = new ContentResponse("475557", "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.", "udDclJoHjfjb8Ekgsd4FDteOkCU.jpg", "Joker");
        dummyResponseTvShow = new ContentResponse("1412", "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.", "gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg", "Arrow");
        movieId = dummyResponseMovie.getMovieId();
        tvShowId = dummyResponseTvShow.getMovieId();


    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void getContentMovie() {

        MutableLiveData<MovieEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(dummyContentMovie);

        when(local.getMovie(movieId)).thenReturn(dummyEntity);
        Resource<MovieEntity> result = LiveDataTestUtil.getValue(appRepository.getMovie(movieId));

        verify(local).getMovie(movieId);
        assertNotNull(result);
        assertNotNull(result.data);
        assertNotNull(result.data.getTitle());
        assertNotNull(result.data.getDescription());
        assertEquals(dummyContentMovie.getDescription(), result.data.getDescription());

    }

    @Test
    public void getContentTvShow() {

        MutableLiveData<TvShowEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(dummyContentTvShow);

        when(local.getTvShow(tvShowId)).thenReturn(dummyEntity);
        Resource<TvShowEntity> result = LiveDataTestUtil.getValue(appRepository.getTvShow(tvShowId));

        verify(local).getTvShow(tvShowId);
        assertNotNull(result);
        assertNotNull(result.data);
        assertNotNull(result.data.getTitle());
        assertNotNull(result.data.getDescription());
        assertEquals(dummyContentTvShow.getDescription(), result.data.getDescription());

    }
}