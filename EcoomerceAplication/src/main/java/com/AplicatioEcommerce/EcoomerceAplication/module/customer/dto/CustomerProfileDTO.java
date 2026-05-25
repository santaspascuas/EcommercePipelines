package com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public class CustomerProfileDTO {

    @NotBlank(message = "El nombre legal es obligatorio")
    @Size(max = 120)
    private String legalName;

    @Size(max = 120)
    private String tradeName;

    @Size(max = 20)
    private String nif;

    @Size(max = 50)
    private String alternativeId;

    @Pattern(regexp = "^[+\\d][\\d\\s\\-]{8,19}$", message = "Número de teléfono inválido")
    private String phone;

    @Size(max = 255)
    private String web;

    private List<String> billingEmails;

    @Size(max = 200)
    private String contactPerson;

    @Size(max = 1000)
    private String notes;

    private String preferredPaymentMethod;

    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal generalDiscount;

    public CustomerProfileDTO() {}

    public String getLegalName() { return legalName; }
    public void setLegalName(String legalName) { this.legalName = legalName; }

    public String getTradeName() { return tradeName; }
    public void setTradeName(String tradeName) { this.tradeName = tradeName; }

    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }

    public String getAlternativeId() { return alternativeId; }
    public void setAlternativeId(String alternativeId) { this.alternativeId = alternativeId; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getWeb() { return web; }
    public void setWeb(String web) { this.web = web; }

    public List<String> getBillingEmails() { return billingEmails; }
    public void setBillingEmails(List<String> billingEmails) { this.billingEmails = billingEmails; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getPreferredPaymentMethod() { return preferredPaymentMethod; }
    public void setPreferredPaymentMethod(String preferredPaymentMethod) { this.preferredPaymentMethod = preferredPaymentMethod; }

    public BigDecimal getGeneralDiscount() { return generalDiscount; }
    public void setGeneralDiscount(BigDecimal generalDiscount) { this.generalDiscount = generalDiscount; }
}
