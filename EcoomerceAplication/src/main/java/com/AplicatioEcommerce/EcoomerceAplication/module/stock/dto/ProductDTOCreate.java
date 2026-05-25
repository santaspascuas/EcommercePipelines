package com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto;

import com.AplicatioEcommerce.EcoomerceAplication.shared.model.CategoryEnum;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.TaxType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductDTOCreate {

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 255)
    private String name;

    @Size(max = 50)
    private String code;

    @Size(max = 2000)
    private String description;

    @DecimalMin("0.00")
    private BigDecimal defaultPrice;

    @Size(max = 50)
    private String unit;

    private CategoryEnum category;

    private TaxType mainTaxType;

    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal mainTaxRate;

    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal irpf;

    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal equivalenceSurcharge;

    public ProductDTOCreate() {}

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
}
