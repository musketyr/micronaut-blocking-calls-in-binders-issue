package com.example.blocking.binders;

import com.agorapulse.gru.Gru;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
class FactsControllerTest {

    @Inject Gru gru;

    @Test
    void testFactIsInjected() throws Throwable {
        gru.verify(t -> t
                .get("/facts")
                .expect(r -> r.json("factResponse.json"))
        );
    }

}