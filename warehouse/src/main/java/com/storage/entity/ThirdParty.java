package com.storage.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "third_party")
public class ThirdParty  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "third_party_gen")
    @SequenceGenerator(name = "third_party_gen", sequenceName = "third_party_seq", initialValue = 10)
    @Column(name = "id", nullable = false)
    private Long id;

    private String thirdParty;
    private String city;

    @OneToMany(mappedBy = "thirdParty")
    private List<StockMovement> stockMovementsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(String thirdParty) {
        this.thirdParty = thirdParty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<StockMovement> getStockMovementsList() {
        return stockMovementsList;
    }

    public void setStockMovementsList(List<StockMovement> stockMovementsList) {
        this.stockMovementsList = stockMovementsList;
    }
}