package com.giomerito.vsis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giomerito.vsis.domain.Usuario;
import com.giomerito.vsis.repositories.UsuarioRepository;
import com.giomerito.vsis.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, ID: "+ id + " Tipo: " + 
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
}
