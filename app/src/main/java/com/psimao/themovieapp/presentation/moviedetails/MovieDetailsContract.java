package com.psimao.themovieapp.presentation.moviedetails;

import com.psimao.themovieapp.presentation.BasePresenter;
import com.psimao.themovieapp.presentation.BaseView;

final class MovieDetailsContract {

    interface View extends BaseView {
        void loadMovieContent(MovieDetailsViewModel model);
        void setFavoriteStatus(boolean status);
        void showError(String error);
        Long movieId();
    }

    interface Presenter extends BasePresenter {
        void onFavoriteButtonClicked(boolean status);
    }
}
