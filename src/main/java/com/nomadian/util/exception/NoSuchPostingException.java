package com.nomadian.util.exception;

public class NoSuchPostingException extends RuntimeException {
	//
	private static final long serialVersionUID = 5867172506387382920L;

	public NoSuchPostingException(String message) {
		super(message); 
	}
}