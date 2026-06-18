package com.AplicatioEcommerce.EcoomerceAplication.shared.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @Column(name = "customer_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "tenant_code", nullable = false, unique = true, updatable = false, length = 36)
    private String tenantCode;

    @Column(name = "schema_name", nullable = false, unique = true, updatable = false, length = 63)
    private String schemaName;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (tenantCode == null) {
            tenantCode = UUID.randomUUID().toString();
        }
        if (schemaName == null) {
            schemaName = "tenant_" + tenantCode.replace("-", "");
        }
    }

    public Tenant() {}

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
