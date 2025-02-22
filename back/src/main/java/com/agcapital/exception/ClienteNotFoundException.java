package com.agcapital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cliente não encontrado")
public class ClienteNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ClienteNotFoundException() { super("Usuário não encontrado");}
	public ClienteNotFoundException(String msg) {super(msg);}

}
