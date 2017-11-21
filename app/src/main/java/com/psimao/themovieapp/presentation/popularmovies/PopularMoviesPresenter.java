package com.psimao.themovieapp.presentation.popularmovies;

import com.psimao.themovieapp.domain.interactor.movies.GetPopularMoviesUseCase;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

class PopularMoviesPresenter implements PopularMoviesContract.Presenter {

    private PopularMoviesContract.View view;
    private GetPopularMoviesUseCase<PopularMoviesViewModel> useCase;

    private Scheduler subscriptionScheduler;
    private Scheduler observerScheduler;

    private Disposable popularMoviesDisposable;

    @Inject
    PopularMoviesPresenter(
            PopularMoviesContract.View view,
            GetPopularMoviesUseCase<PopularMoviesViewModel> useCase,
            Scheduler subscriptionScheduler,
            Scheduler observerScheduler) {
        this.view = view;
        this.useCase = useCase;
        this.subscriptionScheduler = subscriptionScheduler;
        this.observerScheduler = observerScheduler;
    }

    @Override
    public void start() {
        if (view.currentPage() == 0) {
            loadPopularMovies(1);
        }
    }

    @Override
    public void stop() {
        dispose();
    }

    @Override
    public void onMovieClicked(PopularMoviesViewModel model) {
        view.callMovieDetailsScreen(model.getId(), model.getTitle());
    }

    @Override
    public void onScrollEnded() {
        if (!isPopularMoviesLoading()) {
            int nextPage = view.currentPage() + 1;
            loadPopularMovies(nextPage);
        }
    }

    private void loadPopularMovies(int page) {
        popularMoviesDisposable = useCase.createObservable(page).
                observeOn(observerScheduler)
                .subscribeOn(subscriptionScheduler)
                .subscribe(
                        popularMoviesViewModels -> view.addMovies(popularMoviesViewModels, page),
                        throwable -> view.showError(throwable.getMessage())
                );
    }

    private void dispose() {
        if (isPopularMoviesLoading()) {
            popularMoviesDisposable.dispose();
        }
    }

    private boolean isPopularMoviesLoading() {
        return popularMoviesDisposable != null && !popularMoviesDisposable.isDisposed();
    }
}
