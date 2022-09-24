package com.prueba.test.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.test.modelo.Usuario;
import com.prueba.test.servicio.UsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/usuarios")
@RestController
public class UsuarioControlador { // API

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping
	public List <Usuario> getUsuarios() {
		return usuarioService.getUsuarios();
	}
	
	@PostMapping("/registrar")
	public Usuario registrarUsuario(@RequestBody Usuario usuario) throws Exception {
		// System.out.println("jaks" + usuario.toString());
		usuario.setClave(this.bCryptPasswordEncoder.encode(usuario.getClave()));
		// System.out.println("nuevo: " + usuario.toString());
		return usuarioService.guardarUsuario(usuario);
	}

	@DeleteMapping("/{cedula}")
	public void eliminarUsuario(@PathVariable Long cedula) {
		System.out.println("id Long: " + cedula);
		usuarioService.eliminarUsuario(cedula);
	}
	
	@PutMapping("/{cedula}")
	public void actualizarUsuario(@PathVariable Long cedula, @RequestBody Usuario usuarioNuevo) throws Exception {
		System.out.println("numCed: "+cedula);
		usuarioService.actualizarUsuario(cedula, usuarioNuevo);
	}
	
	@GetMapping("/{cedula}")
	public Usuario findById(@PathVariable Long cedula) {
		// System.out.println("resultado: " + usuarioService.obtenerPorCedula(cedula) +
		// "de: " + cedula);
		return usuarioService.obtenerPorCedula(cedula);
	}

	/*
	 * @RequestMapping(value = "api/usuarios/{cedula}", method = RequestMethod.PUT)
	 * public void actualizarUsuario(@PathVariable int cedula, @RequestBody Usuario usuarioAct) 
	 * { usuarioDAO.actualizar(cedula, usuarioAct); }
	 */
}
