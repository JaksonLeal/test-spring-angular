package com.prueba.test.modelo;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2725192556981207875L;
	
	private String authority;
	
	public Authority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}
