package com.psimao.themovieapp.presentation.popularmovies;

import com.psimao.themovieapp.presentation.BasePresenter;
import com.psimao.themovieapp.presentation.BaseView;

import java.util.List;

final class PopularMoviesContract {

    interface View extends BaseView {
        void addMovies(List<PopularMoviesViewModel> movies, int page);

        int currentPage();

        void clear();

        void showInfo(String info);

        void showError(String error);

        void callMovieDetailsScreen(long movieId, String movieTitle);
    }

    interface Presenter extends BasePresenter {
        void onMovieClicked(PopularMoviesViewModel model);

        void onScrollEnded();
    }

}
