package com.storage.dto;

import com.storage.entity.ThirdParty;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "cdi")
public interface ThirdPartyMapper {

    @Mapping(source = "partnerName", target = "thirdParty")
    @Mapping(source = "cityName", target = "city")
    ThirdParty thirdPartyDtoToThirdParty(ThirdPartyDto thirdPartyDto);


    @Mapping(source = "thirdParty", target = "partnerName")
    @Mapping(source = "city", target = "cityName")
    ThirdPartyDto thirdPartyToThirdPartyDto(ThirdParty thirdParty);

    @InheritConfiguration
    List<ThirdPartyDto> thirdPartyListToThirdPartyDtoList(List<ThirdParty> thirdParty);
}
