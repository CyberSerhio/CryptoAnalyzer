package com.cybersergi.cryptoanalyzer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandleDtoResponse {

    private Long id;
    private Long timestamp;
    private Float open;
    private Float high;
    private Float low;
    private Float close;
    private Float volume;
    private String isStruct;


}
