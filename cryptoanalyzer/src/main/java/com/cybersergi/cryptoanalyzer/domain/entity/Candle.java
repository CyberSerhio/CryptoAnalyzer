package com.cybersergi.cryptoanalyzer.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "candles")
@Data
@RequiredArgsConstructor
public class Candle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crypto_id", nullable = false)
    private Crypto crypto;
    private Long cTimestamp;
    private Float cOpen;
    private Float cHigh;
    private Float cLow;
    private Float cClose;
    private Float volume;
    private String isStruct;
}
