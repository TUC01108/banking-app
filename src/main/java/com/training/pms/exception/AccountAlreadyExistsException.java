package com.training.pms.exception;

public class AccountAlreadyExistsException extends RuntimeException {
	public AccountAlreadyExistsException() {
	}
	
	public AccountAlreadyExistsException(String message) {
		super(message);
	}
}
