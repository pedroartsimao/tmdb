package com.psimao.themovieapp.presentation.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.psimao.themovieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder> {

    private ArrayList<PopularMoviesViewModel> data = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_popular_movies, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addData(List<PopularMoviesViewModel> data) {
        int actualPosition = this.data.size();
        this.data.addAll(data);
        notifyItemInserted(actualPosition);
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_movie_title)
        TextView textViewMovieTitle;
        @BindView(R.id.image_view_movie_poster)
        ImageView imageViewMoviePoster;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(PopularMoviesViewModel model, OnItemClickListener listener) {
            textViewMovieTitle.setText(model.getTitle());
            Picasso.with(itemView.getContext())
                    .load(model.getImageUrl())
                    .error(R.drawable.ic_error)
                    .into(imageViewMoviePoster);
            if (listener != null) {
                itemView.setOnClickListener(view -> listener.onItemClick(model));
            }
        }
    }

    interface OnItemClickListener {
        void onItemClick(PopularMoviesViewModel model);
    }
}
