package com.AplicatioEcommerce.EcoomerceAplication.DTO;

public class AdressDTOUpdate {
	private String calle;
    private String localidad;
    private String provincia;
    private String codigoPostal;
    private String pais;
       
    

	public AdressDTOUpdate(String calle, String localidad, String provincia, String codigoPostal,
			String pais) {
		super();
		this.calle = calle;
		this.localidad = localidad;
		this.provincia = provincia;
		this.codigoPostal = codigoPostal;
		this.pais = pais;
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
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
    
    

}
