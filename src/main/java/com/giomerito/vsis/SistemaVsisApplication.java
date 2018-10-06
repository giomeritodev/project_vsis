package com.giomerito.vsis;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giomerito.vsis.domain.Categoria;
import com.giomerito.vsis.repositories.CategoriaRepository;

@SpringBootApplication
public class SistemaVsisApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaVsisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Sorvete");
		Categoria cat2 = new Categoria(null, "Picolé");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}
}
