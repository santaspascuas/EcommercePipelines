package com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto;

import java.math.BigDecimal;
import java.util.List;

public class CustomerProfileDTOResponse {

    private String legalName;
    private String tradeName;
    private String nif;
    private String alternativeId;
    private String phone;
    private String web;
    private List<String> billingEmails;
    private String contactPerson;
    private String notes;
    private String preferredPaymentMethod;
    private BigDecimal generalDiscount;

    public CustomerProfileDTOResponse() {}

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
