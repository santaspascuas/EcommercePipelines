package com.AplicatioEcommerce.EcoomerceAplication.module.stock.service;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Stock;

public interface StockService {

    Stock getStock(Long productId);

    Stock reserveStock(Long productId, Integer quantity);

    Stock releaseStock(Long productId, Integer quantity);
}
