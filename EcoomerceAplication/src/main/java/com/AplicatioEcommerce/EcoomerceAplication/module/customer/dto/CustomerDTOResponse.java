package com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto;

import java.time.LocalDateTime;

public class CustomerDTOResponse {

    private Long id;
    private String email;
    private String role;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CustomerProfileDTOResponse profile;

    public CustomerDTOResponse() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CustomerProfileDTOResponse getProfile() {
		return profile;
	}

	public void setProfile(CustomerProfileDTOResponse profile) {
		this.profile = profile;
	}
    
    


}
