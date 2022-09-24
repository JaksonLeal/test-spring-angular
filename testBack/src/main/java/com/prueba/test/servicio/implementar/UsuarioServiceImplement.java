package com.prueba.test.servicio.implementar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prueba.test.excepciones.UsuarioFoundException;
import com.prueba.test.modelo.Usuario;
import com.prueba.test.repositorio.UsuarioRepository;
import com.prueba.test.servicio.UsuarioService;

@Service // indica que es una clase de servicio que permite inyectar dependencias
public class UsuarioServiceImplement implements UsuarioService {

	@Autowired // inyectar
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Usuario guardarUsuario(Usuario usuario) throws Exception {

		Usuario usuarioLocal = usuarioRepository.findByCedula(usuario.getCedula());

		if (usuarioLocal == null) { // verifica si existe el usuario

			usuarioLocal = usuarioRepository.save(usuario); // guarda en la base de datos

		} else {
			System.out.println("el usuario ya existe");
			throw new UsuarioFoundException("el usuario ya esta presente");
		}
		return usuarioLocal;
	}

	@Override
	public Usuario obtenerUsuarioPorUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

	@Override
	public void eliminarUsuario(Long cedula) {
		usuarioRepository.deleteById(cedula);
	}

	@Override
	public Usuario obtenerPorCedula(Long cedula) {
		return usuarioRepository.findByCedula(cedula);
	}

	@Override
	public void actualizarUsuario(Long cedula, Usuario usuarioNuevo) throws Exception {

		Usuario usuarioAnt = obtenerPorCedula(cedula);
		if (usuarioAnt == null) {
			System.out.println("ni mergas prro");
			return;
		} else if (bCryptPasswordEncoder.matches(usuarioNuevo.getClave(), usuarioAnt.getPassword())) {
			System.out.println("efectivamente: ");
			usuarioNuevo.setClave(usuarioAnt.getPassword());
			usuarioNuevo.setCedula(cedula);
			usuarioAnt = usuarioRepository.save(usuarioNuevo);
			return;
		} else {
			usuarioNuevo.setClave(this.bCryptPasswordEncoder.encode(usuarioNuevo.getClave()));
			usuarioNuevo.setCedula(cedula);
			usuarioAnt = usuarioRepository.save(usuarioNuevo);
		}

	}

	@Override
	public Usuario obtenerUsuarioPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public List<Usuario> getUsuarios() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

}
