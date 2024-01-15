package com.storage.dto;

import com.storage.enums.OperationTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;

@Mapper
interface OperationTypeEnumMapper {
    OperationTypeEnumMapper INSTANCE = Mappers.getMapper(OperationTypeEnumMapper.class);

    default Integer map(OperationTypeEnum value) {
        return value.getValue();
    }

    default OperationTypeEnum mapOperationTypeEnumToOperationTypeDto(Integer intValue) {
        return Arrays.stream(OperationTypeEnum.values())
                .filter(c -> c.getValue() == intValue)
                .findFirst()
                .orElse(null);
    }


}
