package com.storage.entity;

import com.storage.enums.OperationTypeEnum;

import java.io.Serializable;
import java.time.*;
import java.util.Date;
import java.util.Map;

public class DemoSerialDeserial implements Serializable {

    private Long id;

    private Boolean aBoolean;

    private String name;

    //    private boolean aSamallBolean;
//    private int intPrimitive;
//    private char charPrimitive;
    private Float priceFloat;
    private Double priceDouble;
    private LocalDate localDate;
    private OffsetDateTime offsetDateTime;
    private ZonedDateTime zonedDateTime;
    private LocalDateTime dateTime;
    private Map<String, String> stringStringMap;
    private Map<LocalDate, String> localDateStringMap;
    private Duration duration;
    private Instant instant;
    private Period period;
    private int[] intArray;
    private OperationTypeEnum operationTypeEnum;
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Float getPriceFloat() {
        return priceFloat;
    }

    public void setPriceFloat(Float priceFloat) {
        this.priceFloat = priceFloat;
    }

    public Double getPriceDouble() {
        return priceDouble;
    }

    public void setPriceDouble(Double priceDouble) {
        this.priceDouble = priceDouble;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Map<String, String> getStringStringMap() {
        return stringStringMap;
    }

    public void setStringStringMap(Map<String, String> stringStringMap) {
        this.stringStringMap = stringStringMap;
    }

    public Map<LocalDate, String> getLocalDateStringMap() {
        return localDateStringMap;
    }

    public void setLocalDateStringMap(Map<LocalDate, String> localDateStringMap) {
        this.localDateStringMap = localDateStringMap;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public int[] getIntArray() {
        return intArray;
    }

    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }

    public OperationTypeEnum getOperationTypeEnum() {
        return operationTypeEnum;
    }

    public void setOperationTypeEnum(OperationTypeEnum operationTypeEnum) {
        this.operationTypeEnum = operationTypeEnum;
    }
}
