package net.ariflaksito.mymovie.ui.fav.tabs.tvshow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;

import net.ariflaksito.mymovie.R;
import net.ariflaksito.mymovie.data.source.local.entity.TvShowEntity;
import net.ariflaksito.mymovie.ui.detail.DetailActivity;
import net.ariflaksito.mymovie.ui.fav.tabs.MovieFragmentCallback;
import net.ariflaksito.mymovie.utils.GlideApp;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class TvShowPagedAdapter extends PagedListAdapter<TvShowEntity, TvShowPagedAdapter.MovieViewHolder> {

    private MovieFragmentCallback callback;

    public TvShowPagedAdapter(MovieFragmentCallback diffCallback) {
        super(DIFF_CALLBACK);
        this.callback = diffCallback;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new TvShowPagedAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.tvTitle.setText(getItem(position).getTitle());
        holder.tvDescription.setText(getItem(position).getDescription());
        holder.tvDate.setText(String.format("Release %s", getItem(position).getRelease()));
        holder.itemView.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_MOVIE, getItem(position).getMovieId());
            intent.putExtra(DetailActivity.EXTRA_IS_MOVIE, 0);
            context.startActivity(intent);
        });

        GlideApp.with(holder.itemView.getContext())
                .load("http://image.tmdb.org/t/p/w300/" + getItem(position).getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                        .override(270, 270)
                        .fitCenter())
                .fitCenter()
                .into(holder.imgPoster);
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

    private static DiffUtil.ItemCallback<TvShowEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvShowEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem.getMovieId().equals(newItem.getMovieId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

}
