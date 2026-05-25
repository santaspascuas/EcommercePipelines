package com.AplicatioEcommerce.EcoomerceAplication.module.stock.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.GlobalErrorCodeConstants;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.StockException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Stock;
import com.AplicatioEcommerce.EcoomerceAplication.module.stock.repository.StockDao;

@Service
public class StockServiceImplements implements StockService {

    private final StockDao stockdao;
    private static final Logger log = LoggerFactory.getLogger(StockServiceImplements.class);

    public StockServiceImplements(StockDao stockdao) {
        this.stockdao = stockdao;
    }

    @Override
    @Transactional(readOnly = true)
    public Stock getStock(Long productId) {
        return stockdao.findByProductId(productId)
                .orElseThrow(() -> new StockException(GlobalErrorCodeConstants.STOCK_NOT_FOUND, "productId: " + productId));
    }

    @Override
    @Transactional
    public Stock reserveStock(Long productId, Integer quantity) {
        log.info("[reserveStock] productId={}, quantity={}", productId, quantity);
        Stock stock = stockdao.findByProductIdWithLock(productId)
                .orElseThrow(() -> new StockException(GlobalErrorCodeConstants.STOCK_NOT_FOUND, "productId: " + productId));

        if (stock.getAvailableQuantity() < quantity) {
            throw new StockException(GlobalErrorCodeConstants.STOCK_INSUFFICIENT,
                    "Disponible: " + stock.getAvailableQuantity() + ", solicitado: " + quantity);
        }

        stock.setAvailableQuantity(stock.getAvailableQuantity() - quantity);
        stock.setReservedQuantity(stock.getReservedQuantity() + quantity);
        stock.setLastUpdated(LocalDateTime.now());
        return stockdao.save(stock);
    }

    @Override
    @Transactional
    public Stock releaseStock(Long productId, Integer quantity) {
        log.info("[releaseStock] productId={}, quantity={}", productId, quantity);
        Stock stock = stockdao.findByProductIdWithLock(productId)
                .orElseThrow(() -> new StockException(GlobalErrorCodeConstants.STOCK_NOT_FOUND, "productId: " + productId));

        stock.setAvailableQuantity(stock.getAvailableQuantity() + quantity);
        stock.setReservedQuantity(Math.max(0, stock.getReservedQuantity() - quantity));
        stock.setLastUpdated(LocalDateTime.now());
        return stockdao.save(stock);
    }
}
