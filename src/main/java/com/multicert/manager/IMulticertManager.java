package com.multicert.manager;

import com.multicert.bean.MulticertResponse;

public interface IMulticertManager {

	public MulticertResponse getCountryInfo(String countryCode) throws Exception;
}
