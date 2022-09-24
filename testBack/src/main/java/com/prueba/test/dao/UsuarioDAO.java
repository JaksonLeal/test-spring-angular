package com.prueba.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.test.interfaces.IUsuario;
import com.prueba.test.modelo.Usuario;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UsuarioDAO implements IUsuario {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	@Override
	public List<Usuario> getUsuarios() {
		String query = "FROM Usuario"; // hace la consulta - nombre de la clase
		return entityManager.createQuery(query, Usuario.class).getResultList();
	}

	@Override
	public void eliminar(int cedula) {
		String cedulaS = cedula + "";
		Usuario usuario = entityManager.find(Usuario.class, cedulaS);
		entityManager.remove(usuario);
	}

	@Override
	public void registrar(Usuario usuario) {
		entityManager.persist(usuario); // merge para actualizar y persist para agregar
	}
	
	@Override
	public void actualizar(int cedula, Usuario usuarioAct) {
		String cedulaS = cedula + "";
		Usuario usuario = entityManager.find(Usuario.class, cedulaS);
		usuario.setEstado(usuarioAct.getEstado());
		usuario.setPrimerApellido(usuarioAct.getPrimerApellido());
		usuario.setPrimerNombre(usuarioAct.getPrimerNombre());
		usuario.setSegundoApellido(usuarioAct.getSegundoApellido());
		usuario.setSegundoNombre(usuarioAct.getSegundoNombre());
		usuario.setClave(usuarioAct.getClave());
		usuario.setEmail(usuarioAct.getEmail());
	}

	@Override
	public Usuario findByCedula(int cedula) {
		String cedulaS = cedula + "";
		return entityManager.find(Usuario.class, cedulaS);
	}

	@Override
	public Usuario obtenerUsuarioPorLogueo(Usuario usuario) {
		// TODO Auto-generated method stub
		String query = "FROM Usuario WHERE email = :email";	
		List <Usuario> lista =  entityManager.createQuery(query, Usuario.class)
				.setParameter("email", usuario.getEmail())
				.getResultList();	

		if (lista.isEmpty()) return null; //evita null pointer exeption si la lista llega vacia

		System.out.println("lista: "+lista.get(0));
		String contraHashed = lista.get(0).getClave();	
		//Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		
		//if  (argon2.verify(contraHashed, usuario.getClave())) return lista.get(0);
		
		return null;
		
	}

}
