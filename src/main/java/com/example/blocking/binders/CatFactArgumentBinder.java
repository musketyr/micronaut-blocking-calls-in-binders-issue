package com.example.blocking.binders;

import io.micronaut.core.convert.ArgumentConversionContext;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.bind.binders.TypedRequestArgumentBinder;
import jakarta.inject.Singleton;

@Singleton
public class CatFactArgumentBinder implements TypedRequestArgumentBinder<CatFact> {

    private final CatFactClient catFactClient;

    public CatFactArgumentBinder(CatFactClient catFactClient) {
        this.catFactClient = catFactClient;
    }

    @Override
    public Argument<CatFact> argumentType() {
        return Argument.of(CatFact.class);
    }

    @Override
    public BindingResult<CatFact> bind(ArgumentConversionContext<CatFact> context, HttpRequest<?> source) {
        return () -> catFactClient.getFacts().stream().findFirst();
    }
}
