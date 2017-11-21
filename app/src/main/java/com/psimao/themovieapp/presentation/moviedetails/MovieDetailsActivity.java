package com.psimao.themovieapp.presentation.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.psimao.themovieapp.R;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract.View {

    private static final String EXTRA_MOVIE_ID = "extra_movie_details_movie_id";
    private static final String EXTRA_MOVIE_TITLE = "extra_movie_details_movie_title";

    @BindView(R.id.root_view_movie_details)
    CoordinatorLayout rootView;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_view_movie_backdrop)
    ImageView imageViewMovieBackdrop;
    @BindView(R.id.text_view_content)
    TextView textViewContent;
    @BindView(R.id.fab_favorite)
    FloatingActionButton fabFavorite;

    @Inject
    MovieDetailsContract.Presenter presenter;

    private Long movieId;

    private boolean isFavorite;

    public static Intent createIntent(Context context, Long movieId, String movieTitle) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE_ID, movieId);
        intent.putExtra(EXTRA_MOVIE_TITLE, movieTitle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        setupMovieId();
        setupToolbar();
        setupFavoriteButton();
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
    public void loadMovieContent(MovieDetailsViewModel model) {
        collapsingToolbarLayout.setTitle(model.getTitle());
        textViewContent.setText(getString(
                R.string.activity_movie_details_content,
                model.getRuntime(),
                model.getReleaseDate(),
                model.getGenres(),
                model.getOverview(),
                model.getVoteAverage(),
                model.getVoteCount(),
                model.getHomepage() != null && !model.getHomepage().isEmpty() ? model.getHomepage() : getString(R.string.not_available)));
        Picasso.with(this)
                .load(model.getImageUrl())
                .error(R.drawable.ic_error)
                .into(imageViewMovieBackdrop);
        updateFavoriteIcon();
    }

    @Override
    public void setFavoriteStatus(boolean status) {
        isFavorite = status;
        updateFavoriteIcon();
    }

    @Override
    public void showError(String error) {
        Snackbar snackbar = Snackbar.make(rootView, error, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.action_ok, view -> snackbar.dismiss());
        snackbar.show();
    }

    @Override
    public Long movieId() {
        return movieId;
    }

    private void setupMovieId() {
        movieId = getIntent().getLongExtra(EXTRA_MOVIE_ID, -1);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(getIntent().getStringExtra(EXTRA_MOVIE_TITLE));
    }

    private void setupFavoriteButton() {
        fabFavorite.setOnClickListener(view -> {
            isFavorite = !isFavorite;
            updateFavoriteIcon();
            presenter.onFavoriteButtonClicked(isFavorite);
        });
    }

    private void updateFavoriteIcon() {
        fabFavorite.setImageResource(isFavorite ?
                R.drawable.ic_favorite_white : R.drawable.ic_favorite_border_white);
    }
}
