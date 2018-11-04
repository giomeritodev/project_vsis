package com.giomerito.vsis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giomerito.vsis.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
