package com.prueba.test.interfaces;

import java.util.List;

import com.prueba.test.modelo.Usuario; 

public interface IUsuario {
	
	List <Usuario> getUsuarios ();
	void eliminar(int cedula);
	void registrar(Usuario usuario);
	void actualizar (int cedula, Usuario usuarioAct);
	Usuario findByCedula (int cedula);
	Usuario obtenerUsuarioPorLogueo(Usuario usuario);
	
}
