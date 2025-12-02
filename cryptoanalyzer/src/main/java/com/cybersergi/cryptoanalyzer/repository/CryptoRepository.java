package com.cybersergi.cryptoanalyzer.repository;

import com.cybersergi.cryptoanalyzer.domain.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, Long> {
    Crypto findById(Crypto crypto);
}
