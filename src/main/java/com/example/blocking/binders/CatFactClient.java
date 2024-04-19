package com.example.blocking.binders;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import java.util.List;

@Client("https://cat-fact.herokuapp.com")
public interface CatFactClient {

    @Get("/facts")
    List<CatFact> getFacts();
}