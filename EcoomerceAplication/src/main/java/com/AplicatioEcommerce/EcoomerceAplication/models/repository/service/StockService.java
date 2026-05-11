package com.AplicatioEcommerce.EcoomerceAplication.models.repository.service;

import com.AplicatioEcommerce.EcoomerceAplication.models.Stock;

public interface StockService {

    Stock getStock(Long productId);

    Stock reserveStock(Long productId, Integer quantity);

    Stock releaseStock(Long productId, Integer quantity);
}
