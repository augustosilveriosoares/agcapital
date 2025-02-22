package com.agcapital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Este e-mail já esta sendo usado")
public class EmailDuplicateException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public EmailDuplicateException() { super("Este e-mail já esta sendo usado");}
	public EmailDuplicateException(String msg) {super(msg);}

}

