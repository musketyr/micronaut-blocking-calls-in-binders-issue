package com.example.blocking.binders;

import io.micronaut.core.convert.ArgumentConversionContext;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.bind.binders.TypedRequestArgumentBinder;
import io.micronaut.scheduling.TaskExecutors;
import jakarta.inject.Named;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@Singleton
public class CatFactArgumentBinder implements TypedRequestArgumentBinder<CatFact> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatFactArgumentBinder.class);

    private final Provider<CatFactClient> catFactClient;
    private final ExecutorService executorService;

    public CatFactArgumentBinder(Provider<CatFactClient> catFactClient, @Named(TaskExecutors.BLOCKING) ExecutorService executorService) {
        this.catFactClient = catFactClient;
        this.executorService = executorService;
    }

    @Override
    public Argument<CatFact> argumentType() {
        return Argument.of(CatFact.class);
    }

    @Override
    public BindingResult<CatFact> bind(ArgumentConversionContext<CatFact> context, HttpRequest<?> source) {
        return () -> {
            try {
                return executorService.submit(() -> catFactClient.get().getFacts()).get().stream().findAny();
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error("Error fetching cat facts", e);
                return Optional.empty();
            }
        };
    }

}
