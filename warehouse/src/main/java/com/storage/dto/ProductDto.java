package com.storage.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String productName;
    @Min(value = 0, message = "Minimum accepted price is 0.00")
    private BigDecimal productPrice;
    @Length(min = 2, max = 100)
    private String productDescription;
    private Boolean active;

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
