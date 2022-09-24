package com.prueba.test.excepciones;

public class UsuarioFoundException extends Exception {

	/**
	 * autogenerado
	 */
	private static final long serialVersionUID = -1187418085069699373L;

	public UsuarioFoundException() {
		super("El usuario con ese nombre ya existe en la base de datos, vuelva a intentar por favor!");
		// TODO Auto-generated constructor stub
	}

	public UsuarioFoundException(String mensaje) {
		super(mensaje); // TODO Auto-generated constructor stub
	}

}
