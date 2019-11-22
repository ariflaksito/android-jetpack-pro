package net.ariflaksito.mymovie.ui.movies;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;

import net.ariflaksito.mymovie.R;
import net.ariflaksito.mymovie.data.source.local.entity.MovieEntity;
import net.ariflaksito.mymovie.ui.detail.DetailActivity;
import net.ariflaksito.mymovie.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Activity activity;
    private List<MovieEntity> listMovie = new ArrayList<>();

    public MovieAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<MovieEntity> getListMovie() {
        return listMovie;
    }

    public void setListMovie(List<MovieEntity> mMovie) {
        if (mMovie == null) return;
        this.listMovie.clear();
        this.listMovie.addAll(mMovie);
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.tvTitle.setText(getListMovie().get(position).getTitle());
        holder.tvDescription.setText(getListMovie().get(position).getDescription());
        holder.tvDate.setText(String.format("Release %s", getListMovie().get(position).getRelease()));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_MOVIE, getListMovie().get(position).getMovieId());
            intent.putExtra(DetailActivity.EXTRA_IS_MOVIE, 1);
            activity.startActivity(intent);
        });

        GlideApp.with(holder.itemView.getContext())
                .load("http://image.tmdb.org/t/p/w300/"+getListMovie().get(position).getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                        .override(270, 270)
                        .fitCenter())
                .fitCenter()
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
        }
    }
}
