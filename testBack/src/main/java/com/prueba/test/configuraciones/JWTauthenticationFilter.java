package com.prueba.test.configuraciones;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.prueba.test.servicio.implementar.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component  //funciona como un filtro inteceptando todas las peticiones al server con el token
public class JWTauthenticationFilter extends OncePerRequestFilter{

	   @Autowired
	    private UserDetailsServiceImpl userDetailsService;

	    @Autowired
	    private JWTutils jwtUtil;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	       System.out.println("que mamada");
	    	String requestTokenHeader = request.getHeader("Authorization");
	        String username = null;
	        String jwtToken = null;

	        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
	            jwtToken = requestTokenHeader.substring(7);

	            try{
	                username = this.jwtUtil.extractUsername(jwtToken);
	                System.out.println("que mama2 " +username );
	            }catch (ExpiredJwtException exception){
	                System.out.println("El token ha expirado");
	            }catch (Exception e){
	                e.printStackTrace();
	            }

	        }else{
	            System.out.println("Token invalido , no empieza con bearer string");
	        }

	        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
	            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	            if(this.jwtUtil.validateToken(jwtToken, userDetails)){
	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
	        }else{
	            System.out.println("El token no es valido");
	        }
	        filterChain.doFilter(request, response);
	    }
	    
}
