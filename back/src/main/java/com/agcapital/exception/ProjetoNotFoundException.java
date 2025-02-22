package com.agcapital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Projeto Não Encontrado")
public class ProjetoNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ProjetoNotFoundException() { super("Projeto Não Encontrado");}
	public ProjetoNotFoundException(String msg) {super(msg);}

}

