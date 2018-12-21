package com.giomerito.vsis.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
