package com.cybersergi.cryptoanalyzer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoResponseDto {
    private Long id;
    private String title;
}
