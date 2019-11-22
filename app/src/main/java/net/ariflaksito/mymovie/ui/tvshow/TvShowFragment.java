package net.ariflaksito.mymovie.ui.tvshow;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.ariflaksito.mymovie.R;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
import net.ariflaksito.mymovie.viewmodel.ViewModelFactory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TvShowFragment extends Fragment {

    private RecyclerView rvTvShow;
    private ProgressBar progressBar;
    private TvShowAdapter tvShowAdapter;

    private TvShowViewModel viewModel;
    private List<TvShowEntity> tvShows;

    public TvShowFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new TvShowFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTvShow = view.findViewById(R.id.rv_tvshow);
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {

            viewModel = obtainViewModel(getActivity());
            tvShowAdapter = new TvShowAdapter(getActivity());
            viewModel.getTvShow().observe(this, tvShows -> {
                if(tvShows != null){
                    switch (tvShows.status){
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            tvShowAdapter.setListTvShow(tvShows.data);
                            tvShowAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(),"Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            break;

                    }
                }

            });
            
            rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTvShow.setHasFixedSize(true);
            rvTvShow.setAdapter(tvShowAdapter);
        }
    }

    @NonNull
    private static TvShowViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getTvShowInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel.class);
    }

}
