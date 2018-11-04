package com.giomerito.vsis.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giomerito.vsis.domain.Categoria;
import com.giomerito.vsis.domain.Cliente;
import com.giomerito.vsis.repositories.ClienteRepository;
import com.giomerito.vsis.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, ID: " + id + ", Tipo: " + Categoria.class.getName()));
	}
}
