package com.giomerito.vsis.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.giomerito.vsis.domain.Categoria;
import com.giomerito.vsis.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Produto obj "
			+ "INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% "
			+ "AND cat IN :categorias ")
	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	
	
	/* A mesma Consulta com spring data
		Page<Produto> findDistinctNomeContainingAndCategorias(String nome, List<Categoria> categorias, Pageable pageRequest);
	*/
}
