package com.prueba.test.servicio;

import java.util.List;

import com.prueba.test.modelo.Usuario;

public interface UsuarioService {
	
	public List <Usuario> getUsuarios ();
	
	public Usuario guardarUsuario(Usuario usuario) throws Exception;

	public Usuario obtenerUsuarioPorUsername(String username);
	
	public Usuario obtenerUsuarioPorEmail(String email);
	
	public Usuario obtenerPorCedula (Long cedula);
	
	public void eliminarUsuario (Long cedula);
	
	public void actualizarUsuario (Long cedula, Usuario usuarioNuevo) throws Exception;
	
}
