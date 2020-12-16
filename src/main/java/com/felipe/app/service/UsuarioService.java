package com.felipe.app.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.felipe.app.model.Usuario;
import com.felipe.app.repository.UsuarioRepository;
import com.felipe.app.rest.exception.UsuarioCastradoException;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	public void salvar(@RequestBody @Valid Usuario usuario) {
		boolean exists = repository.existsByUsername(usuario.getUsername());
		if(exists) {
			throw new UsuarioCastradoException(usuario.getUsername());
		}
		repository.save(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado!"));
		return User
				.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.roles("USER")
				.build();
	}

}
