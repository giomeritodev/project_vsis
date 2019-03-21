package com.giomerito.vsis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.giomerito.vsis.domain.Usuario;
import com.giomerito.vsis.repositories.UsuarioRepository;
import com.giomerito.vsis.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario obj = repo.findByEmail(email);
		
		if(obj == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(obj.getId(), obj.getEmail(), obj.getSenha(), obj.getPerfis());
	}

}
