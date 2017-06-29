package com.multicert.service;

import com.multicert.bean.MulticertResponse;

public interface IMulticertService {

	public MulticertResponse getCountryInfo(String countryCode) throws Exception;
}
