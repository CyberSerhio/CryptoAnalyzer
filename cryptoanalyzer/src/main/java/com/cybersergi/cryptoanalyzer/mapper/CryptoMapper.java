package com.cybersergi.cryptoanalyzer.mapper;

import com.cybersergi.cryptoanalyzer.domain.dto.CryptoCreateDto;
import com.cybersergi.cryptoanalyzer.domain.dto.CryptoResponseDto;
import com.cybersergi.cryptoanalyzer.domain.entity.Crypto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CryptoMapper {

    @Mapping(target = "id", ignore = true)
    Crypto toEntity(CryptoCreateDto cryptoCreateDto);

    CryptoResponseDto toDto(Crypto crypto);
}
