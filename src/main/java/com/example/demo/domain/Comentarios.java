package com.example.demo.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Comentarios {

	@JsonInclude(Include.NON_NULL)
	private Long id;
	@JsonInclude(Include.NON_NULL)
	private String texto;
	@JsonInclude(Include.NON_NULL)
	private String usuario;
	@JsonInclude(Include.NON_NULL)
	private Date data;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
