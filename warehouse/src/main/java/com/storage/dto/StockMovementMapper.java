package com.storage.dto;

import com.storage.entity.StockMovement;
import com.storage.enums.OperationTypeEnum;
import org.mapstruct.*;

import java.util.Arrays;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "cdi", uses = ThirdPartyMapper.class)
public interface StockMovementMapper {

    StockMovement stockMovementDtoToStockMovement(StockMovementDto stockMovementDto);

    @Mapping(source = "noOfUnits", target = "stockUnits")
    @Mapping(source = "operationDate", target = "stockOperationDate")
    @Mapping(source = "operationType", target = "stockOperation")
    @Mapping(source = "thirdParty", target = "thirdPartyDto")
    StockMovementDto stockMovementToStockMovementDto(StockMovement stockMovement);

    @InheritConfiguration
    List<StockMovementDto> stockMovementListToStockMovementDtoList(List<StockMovement> stockMovementList);

    default int map(OperationTypeEnum value) {
        return value.getValue();
    }

    default OperationTypeEnum map(int value) {
        return Arrays.stream(OperationTypeEnum.values())
                .filter(c -> c.getValue() == value)
                .findFirst()
                .orElse(null);
    }

}
