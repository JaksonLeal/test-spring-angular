package com.prueba.test.modelo;

public class JWTrequest {
	
	private String email;
	private String clave;
	
	public JWTrequest() {
		
	}

	public JWTrequest(String email, String clave) {
		super();
		this.email = email;
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
