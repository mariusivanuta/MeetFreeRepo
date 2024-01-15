package com.storage.service;

import com.storage.entity.StockMovement;
import com.storage.repository.StockMovementRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class StockMovementService {

    @Inject
    StockMovementRepository stockMovementRepository;

    public List<StockMovement> getStockMovementForId(Long productId) {
        return stockMovementRepository.findAllByProductId(productId);
    }
}
