package net.ariflaksito.mymovie.ui.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;

import net.ariflaksito.mymovie.R;
import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
import net.ariflaksito.mymovie.utils.GlideApp;
import net.ariflaksito.mymovie.viewmodel.ViewModelFactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_IS_MOVIE = "extra_is_movie";
    private TextView textTitle;
    private TextView textDesc;
    private ImageView imagePoster;
    private DetailViewModel viewModel;
    private int isMovie;
    private ProgressBar progressBar;
    private Menu menu;
    private String movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        viewModel = obtainViewModel(this);
        textTitle = findViewById(R.id.text_title);
        textDesc = findViewById(R.id.text_description);
        imagePoster = findViewById(R.id.image_poster);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movieId = extras.getString(EXTRA_MOVIE);
            isMovie = extras.getInt(EXTRA_IS_MOVIE);
            boolean movieFlag = isMovie == 1;

            viewModel.setMovieId(movieId);

            if (movieId != null) {

                if(movieFlag){
                    viewModel.getMovie(movieId).observe(this, movieEntity ->{
                        if(movieEntity != null){
                            switch (movieEntity.status){
                                case LOADING:
                                    progressBar.setVisibility(View.VISIBLE);
                                    break;
                                case SUCCESS:
                                    progressBar.setVisibility(View.GONE);
                                    populateDetail(movieEntity.data);
                                    break;
                                case ERROR:
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(this,"Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
                }else{
                    viewModel.getTvShow(movieId).observe(this, tvEntity ->{
                        if(tvEntity != null){
                            switch (tvEntity.status){
                                case LOADING:
                                    progressBar.setVisibility(View.VISIBLE);
                                    break;
                                case SUCCESS:
                                    progressBar.setVisibility(View.GONE);
                                    populateDetail(tvEntity.data);
                                    break;
                                case ERROR:
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(this,"Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
                }
            }

        }

    }

    public void populateDetail(MovieEntity contentEntity) {
        textTitle.setText(contentEntity.getTitle());
        textDesc.setText(contentEntity.getDescription());
        GlideApp.with(getApplicationContext())
                .load("http://image.tmdb.org/t/p/w300/"+contentEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imagePoster);
    }

    public void populateDetail(TvShowEntity contentEntity) {
        textTitle.setText(contentEntity.getTitle());
        textDesc.setText(contentEntity.getDescription());
        GlideApp.with(getApplicationContext())
                .load("http://image.tmdb.org/t/p/w300/"+contentEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imagePoster);
    }

    @NonNull
    private static DetailViewModel obtainViewModel(AppCompatActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getDetailInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(DetailViewModel.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;

        boolean movieFlag = isMovie == 1;
        if(movieFlag){
            viewModel.movieResource.observe(this, movieEntityResource -> {
                if(movieEntityResource != null){
                    switch (movieEntityResource.status){
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            boolean state = movieEntityResource.data.isBookmarked();
                            setFavoriteState(state);
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }


            });
        }else{
            viewModel.tvResource.observe(this, tvShowEntityResource -> {
                if(tvShowEntityResource != null){
                    switch (tvShowEntityResource.status){
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            boolean state = tvShowEntityResource.data.isBookmarked();
                            setFavoriteState(state);
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_bookmark) {

            boolean movieFlag = isMovie == 1;
            if(movieFlag) viewModel.setMovieFavorite();
            else viewModel.setTvShowFavorite();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFavoriteState(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_bookmark);
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_black));
        }
    }
}