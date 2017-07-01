package com.multicert.exception;

public class FindByWeatherNotFoundException extends MulticertBussinessException{

	private static final long serialVersionUID = 1L;

	public FindByWeatherNotFoundException(String message) {
		super(message);
	}
}
