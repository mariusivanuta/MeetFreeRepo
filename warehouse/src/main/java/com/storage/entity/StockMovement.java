package com.storage.entity;

import com.storage.enums.OperationTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="stock_movement")
public class StockMovement  implements Serializable {
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_gen")
    @SequenceGenerator(name = "stock_gen", sequenceName = "stock_seq", initialValue = 10)
    @Column(name = "id", nullable = false)
    private Long id;

    @Version
    private Integer version;

    private LocalDate operationDate;
    private Integer noOfUnits;
    @Enumerated(EnumType.ORDINAL)
    private OperationTypeEnum operationType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "third_party_id")
    private ThirdParty thirdParty;

    @ManyToOne()
    Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ThirdParty getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(ThirdParty thirdParty) {
        this.thirdParty = thirdParty;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }

    public Integer getNoOfUnits() {
        return noOfUnits;
    }

    public void setNoOfUnits(Integer noOfUnits) {
        this.noOfUnits = noOfUnits;
    }

    public OperationTypeEnum getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationTypeEnum operationType) {
        this.operationType = operationType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
