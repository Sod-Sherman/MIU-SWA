package edu.miu.reactiverest.controller;

import edu.miu.reactiverest.model.Stock;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class StockReactiveController {

    @GetMapping(value = "/stocks", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Stock> getAllStocks() {

        Flux<Stock> stockFlux = Flux.just(
                new Stock("001", "Amazon", 3000.00),
                new Stock("002", "Microsoft", 2000.00),
                new Stock("003", "Apple", 1000.00),
                new Stock("004", "Dell", 900.00),
                new Stock("005", "IBM", 700.00)
                ).delayElements(Duration.ofSeconds(2));
        return stockFlux;
    }

}
