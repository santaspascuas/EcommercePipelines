package com.AplicatioEcommerce.EcoomerceAplication.shared.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_profiles")
public class CustomerProfile {

    @Id
    @Column(name = "customer_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false, length = 120)
    private String legalName;

    @Column(length = 120)
    private String tradeName;

    @Column(unique = true, length = 20)
    private String nif;

    @Column(length = 50)
    private String alternativeId;

    @Column(unique = true, length = 20)
    @Pattern(regexp = "^[+\\d][\\d\\s\\-]{8,19}$", message = "Número de teléfono inválido")
    private String phone;

    @Column(length = 255)
    private String web;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "customer_billing_emails", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "billing_email")
    private List<String> billingEmails = new ArrayList<>();

    @Column(length = 200)
    private String contactPerson;

    @Column(length = 1000)
    private String notes;

    private String preferredPaymentMethod;

    @Column(precision = 5, scale = 2)
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal generalDiscount;

    public CustomerProfile() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getAlternativeId() {
		return alternativeId;
	}

	public void setAlternativeId(String alternativeId) {
		this.alternativeId = alternativeId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public List<String> getBillingEmails() {
		return billingEmails;
	}

	public void setBillingEmails(List<String> billingEmails) {
		this.billingEmails = billingEmails;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPreferredPaymentMethod() {
		return preferredPaymentMethod;
	}

	public void setPreferredPaymentMethod(String preferredPaymentMethod) {
		this.preferredPaymentMethod = preferredPaymentMethod;
	}

	public BigDecimal getGeneralDiscount() {
		return generalDiscount;
	}

	public void setGeneralDiscount(BigDecimal generalDiscount) {
		this.generalDiscount = generalDiscount;
	}
    
    


}
