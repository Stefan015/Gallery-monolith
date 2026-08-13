package com.example.demo.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String poruka) {
		super(poruka);
	}
}
