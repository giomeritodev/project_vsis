package com.giomerito.vsis.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.giomerito.vsis.domain.Usuario;
import com.giomerito.vsis.domain.enums.TipoUsuario;

public class UsuarioDTO {
	
	@Autowired
	private TipoUsuario tipoUsu;
	
	private Integer id;
	@NotEmpty(message="O preenchimento é obrigatório!")
	private String nome;
	@NotEmpty(message="O preenchimento é obrigatório!")
	@Email(message="Email inválido!")
	private String email;
	@NotEmpty(message="O preenchimento é obrigatório!")
	private String senha;
	@NotEmpty(message="O preenchimento é obrigatório!")
	private Integer tipo;
	
	public UsuarioDTO(Usuario obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.tipo = tipoUsu.getCod();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
}
