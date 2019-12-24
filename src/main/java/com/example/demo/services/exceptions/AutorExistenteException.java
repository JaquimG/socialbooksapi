package com.example.demo.services.exceptions;

public class AutorExistenteException extends RuntimeException{

	public AutorExistenteException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}
	
	public AutorExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
		// TODO Auto-generated constructor stub
	}


	
}
