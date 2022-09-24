package com.prueba.test.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.test.modelo.Usuario;

//se agregan los metodos de busqueda por la libreria de jpaRepository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { 
//despues del "findBy" se coloca el nombre de la columna de la base de datos y pasa el parametro del modelo
	
	public Usuario findByUsername (String username);
	
	public Usuario findByEmail (String email);
	
	public Usuario findByCedula (Long cedula);
	
}
