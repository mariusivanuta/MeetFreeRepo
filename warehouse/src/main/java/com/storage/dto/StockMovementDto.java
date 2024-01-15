package com.storage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class StockMovementDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate stockOperationDate;
    private BigDecimal stockUnits;
    private Integer stockOperation;
    @JsonProperty("thirdParty")
    private ThirdPartyDto thirdPartyDto;

    private Long productTypeId;

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStockOperationDate() {
        return stockOperationDate;
    }

    public void setStockOperationDate(LocalDate stockOperationDate) {
        this.stockOperationDate = stockOperationDate;
    }

    public BigDecimal getStockUnits() {
        return stockUnits;
    }

    public void setStockUnits(BigDecimal stockUnits) {
        this.stockUnits = stockUnits;
    }

    public ThirdPartyDto getThirdPartyDto() {
        return thirdPartyDto;
    }

    public void setThirdPartyDto(ThirdPartyDto thirdPartyDto) {
        this.thirdPartyDto = thirdPartyDto;
    }

    public Integer getStockOperation() {
        return stockOperation;
    }

    public void setStockOperation(Integer stockOperationDto) {
        this.stockOperation = stockOperationDto;
    }
}
