package com.psimao.themovieapp.presentation.moviedetails;

import com.psimao.themovieapp.domain.interactor.movies.GetMovieDetailsUseCase;
import com.psimao.themovieapp.domain.interactor.user.IsAnUserFavoriteMovieUseCase;
import com.psimao.themovieapp.domain.interactor.user.PutUserFavoriteMovieUseCase;
import com.psimao.themovieapp.domain.interactor.user.RemoveUserFavoriteMovieUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    private MovieDetailsContract.View view;
    private GetMovieDetailsUseCase<MovieDetailsViewModel> movieDetailsUseCase;
    private IsAnUserFavoriteMovieUseCase favoriteMovieUseCase;
    private PutUserFavoriteMovieUseCase putUserFavoriteMovieUseCase;
    private RemoveUserFavoriteMovieUseCase removeUserFavoriteMovieUseCase;

    private Scheduler subscriptionScheduler;
    private Scheduler observerScheduler;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    MovieDetailsPresenter(
            MovieDetailsContract.View view,
            GetMovieDetailsUseCase<MovieDetailsViewModel> movieDetailsUseCase,
            IsAnUserFavoriteMovieUseCase favoriteMovieUseCase,
            PutUserFavoriteMovieUseCase putUserFavoriteMovieUseCase,
            RemoveUserFavoriteMovieUseCase removeUserFavoriteMovieUseCase,
            Scheduler subscriptionScheduler,
            Scheduler observerScheduler) {
        this.view = view;
        this.movieDetailsUseCase = movieDetailsUseCase;
        this.favoriteMovieUseCase = favoriteMovieUseCase;
        this.putUserFavoriteMovieUseCase = putUserFavoriteMovieUseCase;
        this.removeUserFavoriteMovieUseCase = removeUserFavoriteMovieUseCase;
        this.subscriptionScheduler = subscriptionScheduler;
        this.observerScheduler = observerScheduler;
    }

    @Override
    public void start() {
        Long movieId = view.movieId();
        disposables.add(movieDetailsUseCase.createObservable(movieId)
                .observeOn(observerScheduler)
                .subscribeOn(subscriptionScheduler)
                .subscribe(
                        movieDetailsViewModel -> view.loadMovieContent(movieDetailsViewModel),
                        throwable -> view.showError(throwable.getMessage())
                )
        );
        disposables.add(favoriteMovieUseCase.createObservable(movieId)
                .observeOn(observerScheduler)
                .subscribeOn(subscriptionScheduler)
                .subscribe(isFavorite -> view.setFavoriteStatus(isFavorite)
                )
        );
    }

    @Override
    public void stop() {
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    @Override
    public void onFavoriteButtonClicked(boolean status) {
        Long movieId = view.movieId();
        Observable<Void> observable = status ?
                putUserFavoriteMovieUseCase.createObservable(movieId) :
                removeUserFavoriteMovieUseCase.createObservable(movieId);
        disposables.add(observable
                .observeOn(observerScheduler)
                .subscribeOn(subscriptionScheduler)
                .subscribe()
        );
    }
}
