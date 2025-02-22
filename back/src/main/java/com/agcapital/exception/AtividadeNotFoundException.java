package com.agcapital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Atividade não encontrado")
public class AtividadeNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public AtividadeNotFoundException() { super("Atividade não encontrada");}
	public AtividadeNotFoundException(String msg) {super(msg);}

}

