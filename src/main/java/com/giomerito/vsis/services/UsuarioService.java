package com.giomerito.vsis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giomerito.vsis.domain.Usuario;
import com.giomerito.vsis.domain.enums.Perfil;
import com.giomerito.vsis.dto.UsuarioDTO;
import com.giomerito.vsis.repositories.UsuarioRepository;
import com.giomerito.vsis.security.UserSS;
import com.giomerito.vsis.services.exceptions.AuthorizationException;
import com.giomerito.vsis.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, ID: "+ id + " Perfil: " + 
						Usuario.class.getName()));
	}
	
	public List<Usuario> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Usuario insert(Usuario user) {
		user.setId(null);
		return repo.save(user);		
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(
				objDto.getId(), 
				objDto.getNome(), 
				objDto.getEmail(), 
				pe.encode(objDto.getSenha())				
		);
	}
}
