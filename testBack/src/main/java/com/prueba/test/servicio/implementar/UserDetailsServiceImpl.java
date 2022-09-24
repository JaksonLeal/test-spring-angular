package com.prueba.test.servicio.implementar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prueba.test.modelo.Usuario;
import com.prueba.test.repositorio.UsuarioRepository;

@Service //esta clase carga un usuario por el username
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		// TODO Auto-generated method stub
		Usuario usuario = this.usuarioRepository.findByUsername(username);
		
		if (usuario == null) throw new UsernameNotFoundException("usuario no encontrado");
		
		return usuario;
	}
	
}
