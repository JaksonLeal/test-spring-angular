package com.prueba.test.modelo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7079643277954221092L;
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) //indica que sea
	 * autoincrementable y que no se recibe en el json
	 * 
	 * @Column(name = "id") 
	 * private Long id;
	 */

	@Id
	@Column(name = "cedula")
	private Long cedula;

	@Column(name = "primer_nombre")
	private String primerNombre;

	@Column(name = "segundo_nombre")
	private String segundoNombre;

	@Column(name = "primer_apellido")
	private String primerApellido;

	@Column(name = "segundo_apellido")
	private String segundoApellido;

	@Column(name = "clave")
	private String clave;

	@Column(name = "email")
	private String email;

	@Column(name = "nit_empresa")
	private String nitEmpresa;

	@Column(name = "rol_usuario")
	private int rol;

	@Column(name = "estado")
	private int estado;

	@Column(name = "username")
	private String username;

	public Usuario() {

	}

	public Usuario(Long cedula, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String clave, String email, String nitEmpresa, int rol, int estado,
			String username) {
		this.cedula = cedula;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.clave = clave;
		this.email = email;
		this.nitEmpresa = nitEmpresa;
		this.rol = rol;
		this.estado = estado;
		this.username = username;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNitEmpresa() {
		return nitEmpresa;
	}

	public void setNitEmpresa(String nitEmpresa) {
		this.nitEmpresa = nitEmpresa;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Usuario [cedula=" + cedula + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre
				+ ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", clave=" + clave
				+ ", email=" + email + ", nitEmpresa=" + nitEmpresa + ", rol=" + rol + ", estado=" + estado
				+ ", username=" + username + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<Authority> autoridades = new HashSet<Authority>(); // guardar autoridades
		Integer rolAux = getRol();
		autoridades.add(new Authority(rolAux.toString()));
		return autoridades;
	}

	@Override
	public String getPassword() { // asigna valor a password de userDetails
		// TODO Auto-generated method stub
		return this.clave;
	}

	@Override
	public String getUsername() { // asigna valor a username que se usa para iniciar sesion
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true; // asigna estado habilidado o desahbilidado
	}

}
