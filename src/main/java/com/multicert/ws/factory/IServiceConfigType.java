package com.multicert.ws.factory;

public enum IServiceConfigType {

	COUNTRY_INFO("countryInfo"), CITIES("cities"), FIND_NEAR_BY_WEATHER("findNearByWeather");

	private String method;

	private IServiceConfigType(String method){
		this.method = method;
	}

	public String getMethod(){
		return this.method;
	}
}
