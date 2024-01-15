package com.storage.enums;

public enum OperationTypeEnum {
    ADD(1), DISCARD(2);
    private int value;

    OperationTypeEnum(int value) {
        this.value = value;
    }

    OperationTypeEnum() {
    }

    public int getValue() {
        return value;
    }

}
