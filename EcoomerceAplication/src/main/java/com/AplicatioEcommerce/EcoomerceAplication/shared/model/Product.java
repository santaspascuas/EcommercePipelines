package com.AplicatioEcommerce.EcoomerceAplication.shared.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "productId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(unique = true, length = 50)
    private String code;

    @Column(length = 2000)
    private String description;

    @Column(precision = 10, scale = 2)
    @DecimalMin("0.00")
    private BigDecimal defaultPrice;

    @Column(length = 50)
    private String unit;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Enumerated(EnumType.STRING)
    private TaxType mainTaxType;

    @Column(precision = 5, scale = 2)
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal mainTaxRate;

    @Column(precision = 5, scale = 2)
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal irpf;

    @Column(precision = 5, scale = 2)
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal equivalenceSurcharge;

    @Column(nullable = false)
    private boolean active = true;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Product() {}

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

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}
