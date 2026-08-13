package com.example.demo.exceptions;

public class AdminOperationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AdminOperationException(String poruka) {
		super(poruka);
	}
}
