package com.cybersergi.cryptoanalyzer.service;

import com.cybersergi.cryptoanalyzer.domain.dto.CandleCreateDto;
import com.cybersergi.cryptoanalyzer.domain.dto.CryptoCreateDto;
import com.cybersergi.cryptoanalyzer.domain.dto.CryptoResponseDto;
import com.cybersergi.cryptoanalyzer.domain.entity.Candle;
import com.cybersergi.cryptoanalyzer.domain.entity.Crypto;
import com.cybersergi.cryptoanalyzer.mapper.CandleMapper;
import com.cybersergi.cryptoanalyzer.mapper.CryptoMapper;
import com.cybersergi.cryptoanalyzer.repository.CandleRepository;
import com.cybersergi.cryptoanalyzer.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BybitService {

    private final CandleMapper candleMapper;
    private final CandleRepository candleRepository;
    private final CryptoMapper cryptoMapper;
    private final CryptoRepository cryptoRepository;

    public Candle createCandle(CandleCreateDto dto) {
        Candle c = candleMapper.toEntity(dto);
        return candleRepository.save(c);
    }

    public List<Candle> saveCandles(List<List<String>> data, Crypto crypto) {
        List<CandleCreateDto> candles = data.stream()
                .map(el -> new CandleCreateDto(
                        crypto,
                        Float.parseFloat(el.get(1)),
                        Float.parseFloat(el.get(2)),
                        Float.parseFloat(el.get(3)),
                        Float.parseFloat(el.get(4)),
                        new Date(Long.parseLong(el.getFirst()))
                ))
                .toList();
        List<Candle> entities = candleMapper.toEntities(candles);
        return candleRepository.saveAll(entities);
    }

    public String getBybitData(String symbol) {
        Crypto crypto = createCrypto(symbol);

        String urlString = "https://api.bybit.com/v5/market/kline" +
                "?category=linear&" + "symbol=" + symbol + "&interval=15&limit=1000";
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
            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Crypto createCrypto(String symbol) {
        CryptoCreateDto cryptoCreateDto = new CryptoCreateDto(symbol);
        Crypto entity = cryptoMapper.toEntity(cryptoCreateDto);
        return cryptoRepository.save(entity);
    }

}
