package com.psimao.themovieapp.domain.interactor;

import io.reactivex.Observable;

/**
 * Base abstract class for Use cases.
 *
 * @param <R> Expected result type
 * @param <P> Request params
 */
public abstract class BaseUseCase<R, P> {

    public abstract Observable<R> createObservable(P params);
}
