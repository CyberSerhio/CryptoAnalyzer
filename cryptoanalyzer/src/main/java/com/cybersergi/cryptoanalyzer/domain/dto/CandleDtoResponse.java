package com.cybersergi.cryptoanalyzer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandleDtoResponse {

    private Long id;
    private Float c_high;
    private Float c_open;
    private Float c_close;
    private Float c_low;
    private Date c_timestamp;
    private String isStruct;

}
