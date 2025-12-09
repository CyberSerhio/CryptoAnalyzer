package com.cybersergi.cryptoanalyzer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandleDtoResponse {

    private Long id;
    private Long cTimestamp;
    private Float cOpen;
    private Float cHigh;
    private Float cLow;
    private Float cClose;
    private Float volume;
    private String isStruct;


}
