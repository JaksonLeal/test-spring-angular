package com.prueba.test.controlador;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.test.configuraciones.JWTutils;
import com.prueba.test.excepciones.UsuarioNotFoundException;
import com.prueba.test.modelo.JWTrequest;
import com.prueba.test.modelo.JWTresponse;
import com.prueba.test.modelo.Usuario;
import com.prueba.test.repositorio.UsuarioRepository;
import com.prueba.test.servicio.implementar.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JWTutils jwtUtils;
    
    @Autowired
	private UsuarioRepository usuarioRepository;

    @PostMapping("/generate-token") //la misma ruta de MySecurityConfig en .antMatchers("/generate-token", "/usuarios/").permitAll()
    public ResponseEntity<?> generarToken(@RequestBody JWTrequest jwtRequest) throws Exception {
        
    	//System.out.println("generarToken-->"+jwtRequest.getClave()+"email:"+jwtRequest.getEmail());
		Usuario usuario = this.usuarioRepository.findByEmail(jwtRequest.getEmail());
		if (usuario == null) throw new UsernameNotFoundException("usuario no encontrado en generarToken");
		
    	try{
            autenticar(usuario.getUsername(), jwtRequest.getClave());
        }catch (UsuarioNotFoundException exception){
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(usuario.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JWTresponse(token));
    }

    private void autenticar(String username, String clave) throws Exception {
    	System.out.println("autenticar-->"+"password:"+ clave +"-email:"+ username);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, clave));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }

    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal){
    	System.out.println("siyo");
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
