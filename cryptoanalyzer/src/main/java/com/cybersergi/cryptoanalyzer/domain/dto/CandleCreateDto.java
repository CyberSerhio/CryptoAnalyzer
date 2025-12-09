package com.cybersergi.cryptoanalyzer.domain.dto;

import com.cybersergi.cryptoanalyzer.domain.entity.Crypto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandleCreateDto {
    private Crypto crypto;
    private Long cTimestamp;
    private Float cOpen;
    private Float cHigh;
    private Float cLow;
    private Float cClose;
    private Float volume;
    private String isStruct;

}
