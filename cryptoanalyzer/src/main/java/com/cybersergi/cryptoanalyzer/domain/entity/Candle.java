package com.cybersergi.cryptoanalyzer.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "candles")
@Data
public class Candle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float c_high;
    private float c_open;
    private float c_close;
    private float c_low;
    private Date c_timestamp;
}
