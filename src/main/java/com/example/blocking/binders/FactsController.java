package com.example.blocking.binders;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.Map;

@Controller("/facts")
@ExecuteOn(TaskExecutors.BLOCKING)
public class FactsController {


    @Get("/")
    public Map<String, String> index(CatFact fact) {
        return Map.of("text", fact.getText());
    }

}
