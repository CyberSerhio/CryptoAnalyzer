package com.cybersergi.cryptoanalyzer.api;

import com.cybersergi.cryptoanalyzer.service.BybitService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CandleController {

    private final BybitService bybitService;

    @GetMapping("/api/symbol")
    public JSONArray getCandles(@RequestParam String symbols,
                                 @RequestParam String limit,
                                 @RequestParam String interval) {
        return bybitService.getBybitData(symbols, limit, interval);
    }

}
