package com.agcapital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Projeto possui atividades pendentes")
public class ProjetoWithActivityException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ProjetoWithActivityException() { super("Projeto possui atividades pendentes");}
	public ProjetoWithActivityException(String msg) {super(msg);}

}


