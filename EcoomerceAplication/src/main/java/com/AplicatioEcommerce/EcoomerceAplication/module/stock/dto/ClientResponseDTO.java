package com.AplicatioEcommerce.EcoomerceAplication.module.stock.dto;

import java.time.LocalDateTime;

public class ClientResponseDTO {

    private Long clientId;
    private String legalName;
    private String tradeName;
    private String nif;
    private String alternativeId;
    private String email;
    private String phone;
    private String web;
    private String contactPerson;
    private String notes;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ClientResponseDTO() {}

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public String getLegalName() { return legalName; }
    public void setLegalName(String legalName) { this.legalName = legalName; }

    public String getTradeName() { return tradeName; }
    public void setTradeName(String tradeName) { this.tradeName = tradeName; }

    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }

    public String getAlternativeId() { return alternativeId; }
    public void setAlternativeId(String alternativeId) { this.alternativeId = alternativeId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getWeb() { return web; }
    public void setWeb(String web) { this.web = web; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
