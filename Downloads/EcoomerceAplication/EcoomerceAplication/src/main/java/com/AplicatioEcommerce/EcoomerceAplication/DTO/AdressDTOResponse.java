package com.AplicatioEcommerce.EcoomerceAplication.DTO;

public class AdressDTOResponse {
	
	private Long addressId;
	private String calle;
	private String localidad;
	private String provincia;
	private String codigoPostal;
	private String pais;
	
	public AdressDTOResponse(Long addressId, String calle, String localidad, String provincia, String codigoPostal,
			String pais) {
		super();
		this.addressId = addressId;
		this.calle = calle;
		this.localidad = localidad;
		this.provincia = provincia;
		this.codigoPostal = codigoPostal;
		this.pais = pais;
	}




	public String getPais() {
		return pais;
	}




	public void setPais(String pais) {
		this.pais = pais;
	}




	public AdressDTOResponse() {}
	
	
	
	
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	
	

}
