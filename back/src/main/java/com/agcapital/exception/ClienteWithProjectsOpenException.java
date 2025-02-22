package com.agcapital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Não é permitido excluir clientes com projetos em aberto")
public class ClienteWithProjectsOpenException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ClienteWithProjectsOpenException() { super("Não é permitido excluir clientes com projetos em aberto");}
	public ClienteWithProjectsOpenException(String msg) {super(msg);}

}

