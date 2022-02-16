package com.training.pms.exception;

public class InvalidLoginException extends RuntimeException {
	public InvalidLoginException() {
	}
	
	public InvalidLoginException(String message) {
		super(message);
	}
}
