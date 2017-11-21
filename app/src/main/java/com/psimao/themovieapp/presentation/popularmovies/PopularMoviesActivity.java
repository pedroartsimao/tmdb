package com.psimao.themovieapp.presentation.popularmovies;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.psimao.themovieapp.R;
import com.psimao.themovieapp.presentation.moviedetails.MovieDetailsActivity;
import com.psimao.themovieapp.ui.EndlessScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class PopularMoviesActivity extends AppCompatActivity implements PopularMoviesContract.View {

    private static final int RECYCLER_GRID_PORTRAIT_COLUMNS_NUMBER = 3;
    private static final int RECYCLER_GRID_LANDSCAPE_COLUMNS_NUMBER = 5;

    @BindView(R.id.root_view_popular_movies)
    CoordinatorLayout rootView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_popular_movies)
    RecyclerView recyclerViewPopularMovies;
    @BindView(R.id.text_view_info)
    TextView textViewInfo;

    @Inject
    PopularMoviesContract.Presenter presenter;

    private PopularMoviesAdapter adapter = new PopularMoviesAdapter();

    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void addMovies(List<PopularMoviesViewModel> movies, int page) {
        recyclerViewPopularMovies.setVisibility(View.VISIBLE);
        textViewInfo.setVisibility(View.GONE);
        adapter.addData(movies);
        currentPage = page;
    }

    @Override
    public int currentPage() {
        return currentPage;
    }

    @Override
    public void clear() {
        adapter.clear();
    }

    @Override
    public void showInfo(String info) {
        recyclerViewPopularMovies.setVisibility(View.GONE);
        textViewInfo.setVisibility(View.VISIBLE);
        textViewInfo.setText(info);
    }

    @Override
    public void showError(String error) {
        if (textViewInfo.getVisibility() == View.VISIBLE) {
            textViewInfo.setText(error);
        } else {
            Snackbar snackbar = Snackbar.make(rootView, error, Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(R.string.action_ok, view -> snackbar.dismiss());
            snackbar.show();
        }
    }

    @Override
    public void callMovieDetailsScreen(long movieId, String movieTitle) {
        startActivity(MovieDetailsActivity.createIntent(this, movieId, movieTitle));
    }

    private void setupRecyclerView() {
        int spanSize = calculateRecyclerSpanSize();
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanSize);
        recyclerViewPopularMovies.setLayoutManager(layoutManager);
        recyclerViewPopularMovies.addOnScrollListener(new EndlessScrollListener(layoutManager) {
            @Override
            public void onScrollEndReached(int currentPage) {
                presenter.onScrollEnded();
            }
        });
        adapter.setOnItemClickListener(model -> presenter.onMovieClicked(model));
        recyclerViewPopularMovies.setAdapter(adapter);
    }

    private int calculateRecyclerSpanSize() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ?
                RECYCLER_GRID_PORTRAIT_COLUMNS_NUMBER : RECYCLER_GRID_LANDSCAPE_COLUMNS_NUMBER;
    }
}
