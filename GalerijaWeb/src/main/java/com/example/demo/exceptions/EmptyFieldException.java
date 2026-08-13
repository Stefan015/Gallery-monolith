package com.example.demo.exceptions;

public class EmptyFieldException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmptyFieldException(String poruka) {
		super(poruka);
	}
}
