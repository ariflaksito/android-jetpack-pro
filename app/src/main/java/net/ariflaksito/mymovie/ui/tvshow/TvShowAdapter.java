package net.ariflaksito.mymovie.ui.tvshow;

import android.app.Activity;
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
import net.ariflaksito.mymovie.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private final Activity activity;
    private List<TvShowEntity> listTvShow = new ArrayList<>();

    public TvShowAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<TvShowEntity> getListTvShow() {
        return listTvShow;
    }

    public void setListTvShow(List<TvShowEntity> mTvShow) {
        if (mTvShow == null) return;
        this.listTvShow.clear();
        this.listTvShow.addAll(mTvShow);
    }

    @NonNull
    @Override
    public TvShowAdapter.TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.TvShowViewHolder holder, int position) {
        holder.tvTitle.setText(getListTvShow().get(position).getTitle());
        holder.tvDescription.setText(getListTvShow().get(position).getDescription());
        holder.tvDate.setText(String.format("Release %s", getListTvShow().get(position).getRelease()));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_MOVIE, getListTvShow().get(position).getMovieId());
            intent.putExtra(DetailActivity.EXTRA_IS_MOVIE, 0);
            activity.startActivity(intent);
        });

        GlideApp.with(holder.itemView.getContext())
                .load("http://image.tmdb.org/t/p/w300/"+getListTvShow().get(position).getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                        .override(270, 270)
                        .fitCenter())
                .fitCenter()
                .into(holder.imgPoster);
    }


    @Override
    public int getItemCount() {
        return getListTvShow().size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        public TvShowViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
        }
    }
}
