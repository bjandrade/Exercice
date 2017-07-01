package com.multicert.exception;

public class CountryNotFoundException extends MulticertBussinessException{

	private static final long serialVersionUID = 1L;

	public CountryNotFoundException(String message) {
		super(message);
	}
}
