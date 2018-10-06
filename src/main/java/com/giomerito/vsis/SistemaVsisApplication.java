package com.giomerito.vsis;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giomerito.vsis.domain.Categoria;
import com.giomerito.vsis.domain.Produto;
import com.giomerito.vsis.repositories.CategoriaRepository;
import com.giomerito.vsis.repositories.ProdutoRepository;

@SpringBootApplication
public class SistemaVsisApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaVsisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Sorvete");
		Categoria cat2 = new Categoria(null, "Picolé");
		
		Produto p1 = new Produto(null, "Sorvete 200ml", 1.50);
		Produto p2 = new Produto(null, "Picolé cremoso", 0.75);
		Produto p3 = new Produto(null, "Picolé fruta", 0.75);
		
		
		//Relacionado as categorias com os produtos
		cat1.getProdutos().addAll(Arrays.asList(p1));
		cat2.getProdutos().addAll(Arrays.asList(p2, p3));
		
		//Relacionando os produtos com as Categorias
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat2));		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}
}
