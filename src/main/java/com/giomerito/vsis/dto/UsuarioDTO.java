package com.giomerito.vsis.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.giomerito.vsis.domain.Usuario;
import com.giomerito.vsis.domain.enums.TipoUsuario;

public class UsuarioDTO {
	
	@Autowired
	private TipoUsuario tipoUsu;
	
	private Integer id;
	private String nome;
	private String usuario;	
	private Integer tipo;
	
	public UsuarioDTO(Usuario obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.usuario = obj.getUsuario();
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
}
