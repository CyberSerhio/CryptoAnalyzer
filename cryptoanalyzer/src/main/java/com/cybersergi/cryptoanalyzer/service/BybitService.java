package com.cybersergi.cryptoanalyzer.service;

import com.cybersergi.cryptoanalyzer.domain.dto.CandleCreateDto;
import com.cybersergi.cryptoanalyzer.domain.dto.CryptoCreateDto;
import com.cybersergi.cryptoanalyzer.domain.entity.Candle;
import com.cybersergi.cryptoanalyzer.domain.entity.Crypto;
import com.cybersergi.cryptoanalyzer.mapper.CandleMapper;
import com.cybersergi.cryptoanalyzer.mapper.CryptoMapper;
import com.cybersergi.cryptoanalyzer.repository.CandleRepository;
import com.cybersergi.cryptoanalyzer.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Service;
import java.io.IOException;
import org.json.JSONObject;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class BybitService {
    private static final Logger log = LoggerFactory.getLogger(BybitService.class);

    private final CandleMapper candleMapper;
    private final CandleRepository candleRepository;
    private final CryptoMapper cryptoMapper;
    private final CryptoRepository cryptoRepository;

    public Candle createCandle(CandleCreateDto dto) {
        Candle c = candleMapper.toEntity(dto);
        return candleRepository.save(c);
    }

    public List<Candle> saveCandles(JSONArray data, Crypto crypto) {
        List<CandleCreateDto> candles = data.toList().stream()
                .map(obj -> (List<String>) obj)
                .map(el -> new CandleCreateDto(
                        crypto,
                        Long.parseLong(el.getFirst()),
                        Float.parseFloat(el.get(1)),
                        Float.parseFloat(el.get(2)),
                        Float.parseFloat(el.get(3)),
                        Float.parseFloat(el.get(4)),
                        Float.parseFloat(el.get(5)),
                        ""
                ))
                .toList();
        List<Candle> entities = candleMapper.toEntities(candles);
        return candleRepository.saveAll(entities);
    }

    private Crypto createCrypto(String symbol) {
        CryptoCreateDto cryptoCreateDto = new CryptoCreateDto(symbol);
        Crypto entity = cryptoMapper.toEntity(cryptoCreateDto);
        return cryptoRepository.save(entity);
    }

    public JSONArray getBybitData(String symbols, String interval, String limit) {

        String cleanSymbol = symbols.replaceAll("[^\\p{L}]+", "");
        String urlString = null;
        JSONObject jsonObject = null;
        Crypto crypto = cryptoRepository.findByTitle(cleanSymbol);
        if (crypto == null) {
            crypto = createCrypto(cleanSymbol);
            urlString = createUrlString(cleanSymbol, interval, limit);
            jsonObject = sendRequest(urlString);
            if (!jsonObject.has("result")) {
                throw new RuntimeException("Bybit API error: " + jsonObject);
            }
            saveCandles(jsonObject.getJSONArray("result"), crypto);
        } else {
            Long newLimit = updateCandles(crypto, Short.parseShort(interval));
        }


        if (!jsonObject.has("result")) {
            throw new RuntimeException("Bybit API error: " + jsonObject);
        }

        return jsonObject.getJSONArray("result");
    }


    private Long updateCandles(Crypto crypto, Short interval) {
        Candle lastCandle = candleRepository.findTopByCryptoOrderByCTimestampDesc(crypto);
        if (lastCandle != null) {
            Long time = (Instant.now().toEpochMilli() - lastCandle.getCTimestamp())/60000;
            if (time > interval) {
                return time / interval;
            }
        }
        return 0L;
    }

    private JSONObject sendRequest(String urlString) {
        try {
            URI url = new URI(urlString);
            // Create request to Bybit server
            HttpRequest request = HttpRequest.newBuilder(url)
                    .timeout(Duration.of(10, ChronoUnit.SECONDS))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
//                        Catch response from Bybit
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String createUrlString(String symbols, String interval, String limit) {
        try {
            Short.parseShort(limit);
            Short.parseShort(interval);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return "https://api.bybit.com/v5/market/kline" +
                "?category=linear&" + "symbol=" + symbols + "&interval=" + interval + "&limit=" + limit;
    }

}
