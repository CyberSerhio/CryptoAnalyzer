package com.cybersergi.cryptoanalyzer.api;

import com.cybersergi.cryptoanalyzer.domain.entity.Candle;
import com.cybersergi.cryptoanalyzer.repository.CandleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    private final CandleRepository candleRepository;

    Test(CandleRepository candleRepository) {
        this.candleRepository = candleRepository;
    }

    @GetMapping("/api/test")
    Iterable<Candle> getCandles() {
        return candleRepository.findAll();
    }

}
