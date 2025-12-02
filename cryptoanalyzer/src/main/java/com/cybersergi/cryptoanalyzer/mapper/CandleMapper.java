package com.cybersergi.cryptoanalyzer.mapper;

import com.cybersergi.cryptoanalyzer.domain.dto.CandleCreateDto;
import com.cybersergi.cryptoanalyzer.domain.dto.CandleDtoResponse;
import com.cybersergi.cryptoanalyzer.domain.entity.Candle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandleMapper {
    // mapper for create new candle from DTO to entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isStruct", ignore = true)
    Candle toEntity(CandleCreateDto candleCreateDto);

    List<Candle> toEntities(List<CandleCreateDto> dtoList);

    CandleDtoResponse toDto(Candle candle);

    List<CandleDtoResponse> toDtoList(List<Candle> candles);
}
