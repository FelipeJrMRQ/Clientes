package com.felipe.app.rest.exception;

public class UsuarioCastradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsuarioCastradoException(String login) {
		super("Usuario já cadastrado para o login "+ login);
	}
}
