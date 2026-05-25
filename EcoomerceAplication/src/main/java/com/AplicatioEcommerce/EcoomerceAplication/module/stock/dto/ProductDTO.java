package com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.TaxType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDTO {

    private Long id;
    private String name;
    private String code;
    private String description;
    private BigDecimal defaultPrice;
    private String unit;
    private CategoryEnum category;
    private TaxType mainTaxType;
    private BigDecimal mainTaxRate;
    private BigDecimal irpf;
    private BigDecimal equivalenceSurcharge;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getDefaultPrice() { return defaultPrice; }
    public void setDefaultPrice(BigDecimal defaultPrice) { this.defaultPrice = defaultPrice; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public CategoryEnum getCategory() { return category; }
    public void setCategory(CategoryEnum category) { this.category = category; }

    public TaxType getMainTaxType() { return mainTaxType; }
    public void setMainTaxType(TaxType mainTaxType) { this.mainTaxType = mainTaxType; }

    public BigDecimal getMainTaxRate() { return mainTaxRate; }
    public void setMainTaxRate(BigDecimal mainTaxRate) { this.mainTaxRate = mainTaxRate; }

    public BigDecimal getIrpf() { return irpf; }
    public void setIrpf(BigDecimal irpf) { this.irpf = irpf; }

    public BigDecimal getEquivalenceSurcharge() { return equivalenceSurcharge; }
    public void setEquivalenceSurcharge(BigDecimal equivalenceSurcharge) { this.equivalenceSurcharge = equivalenceSurcharge; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
