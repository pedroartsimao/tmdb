package com.psimao.themovieapp.domain.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseMapper<I, R> {

    public abstract R transform(I input);

    public Collection<R> transform(Collection<I> inputList) {
        Collection<R> resultCollection;

        if (inputList != null && !inputList.isEmpty()) {
            resultCollection = new ArrayList<>();
            for(I input: inputList) {
                resultCollection.add(transform(input));
            }
        } else {
            resultCollection = Collections.emptyList();
        }

        return resultCollection;
    }
}
