package net.ariflaksito.mymovie.ui.home;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.ariflaksito.mymovie.R;
import net.ariflaksito.mymovie.ui.fav.FavoriteFragment;
import net.ariflaksito.mymovie.ui.movies.MovieFragment;
import net.ariflaksito.mymovie.ui.tvshow.TvShowFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity {

    private final String SELECTED_MENU = "selected_menu";
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState != null) {
            savedInstanceState.getInt(SELECTED_MENU);
        } else {
            navView.setSelectedItemId(R.id.action_movie);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_MENU, navView.getSelectedItemId());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        Fragment fragment = null;
        if (item.getItemId() == R.id.action_movie) {
            fragment = MovieFragment.newInstance();
        }else if(item.getItemId() == R.id.action_tvshow){
            fragment = TvShowFragment.newInstance();
        }else if(item.getItemId() == R.id.action_fav){
            fragment = new FavoriteFragment();
        }

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container, fragment)
                    .commit();
        }
        return true;
    };

}
