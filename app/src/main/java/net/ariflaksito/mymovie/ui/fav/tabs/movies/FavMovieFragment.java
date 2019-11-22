package net.ariflaksito.mymovie.ui.fav.tabs.movies;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.ariflaksito.mymovie.R;
import net.ariflaksito.mymovie.ui.fav.tabs.MovieFragmentCallback;
import net.ariflaksito.mymovie.viewmodel.ViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavMovieFragment extends Fragment implements MovieFragmentCallback {

    private RecyclerView rvMovie;
    private ProgressBar progressBar;
    private MoviePagedAdapter moviePagedAdapter;
    FavMovieViewModel viewModel;

    public FavMovieFragment() { }

    public static Fragment newInstance() {
        return new FavMovieFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {

            viewModel = obtainViewModel(getActivity());
            moviePagedAdapter = new MoviePagedAdapter(this);

            viewModel.getFavMovies().observe(this, movies -> {
                if(movies != null){
                    switch (movies.status){
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            moviePagedAdapter.submitList(movies.data);
                            moviePagedAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(),"Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });


            rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovie.setHasFixedSize(true);
            rvMovie.setAdapter(moviePagedAdapter);
        }
    }

    @NonNull
    private static FavMovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getFavMovieInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FavMovieViewModel.class);
    }

}
