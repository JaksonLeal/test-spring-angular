package com.prueba.test.excepciones;

public class UsuarioNotFoundException extends Exception{

	public UsuarioNotFoundException() {
		super("El usuario con ese nombre no existe en la base de datos, vuelva a intentar por favor!");
		// TODO Auto-generated constructor stub
	}

	public UsuarioNotFoundException(String mensaje) {
		super(mensaje); // TODO Auto-generated constructor stub
	}
	
}
