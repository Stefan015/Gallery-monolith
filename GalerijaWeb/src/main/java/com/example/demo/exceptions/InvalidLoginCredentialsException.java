package com.example.demo.exceptions;

public class InvalidLoginCredentialsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidLoginCredentialsException(String poruka) {
		super(poruka);
	}
}
