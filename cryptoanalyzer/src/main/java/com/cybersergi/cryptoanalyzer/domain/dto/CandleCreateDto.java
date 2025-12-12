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
    private Long timestamp;
    private Float open;
    private Float high;
    private Float low;
    private Float close;
    private Float volume;
    private String isStruct;

}
