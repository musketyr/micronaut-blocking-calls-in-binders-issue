package com.example.blocking.binders;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Map;

@Controller("/facts")
public class FactsController {


    @Get("/")
    public Map<String, String> index(CatFact fact) {
        return Map.of("text", fact.getText());
    }

}
