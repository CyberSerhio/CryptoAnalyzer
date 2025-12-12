package com.cybersergi.cryptoanalyzer.repository;

import com.cybersergi.cryptoanalyzer.domain.entity.Candle;
import com.cybersergi.cryptoanalyzer.domain.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandleRepository extends JpaRepository<Candle, Long> {
    List<Candle> findAll();
    Candle findTopByCryptoOrderByTimestampDesc(Crypto crypto);
}
