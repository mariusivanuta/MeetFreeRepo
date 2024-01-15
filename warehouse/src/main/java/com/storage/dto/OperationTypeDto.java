package com.storage.dto;

import java.io.Serializable;

class OperationTypeDto implements Serializable {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
