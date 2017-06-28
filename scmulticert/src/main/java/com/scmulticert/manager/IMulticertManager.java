package com.scmulticert.manager;

import com.scmulticert.bean.MulticertResponse;

public interface IMulticertManager {

	public MulticertResponse getCountryInfo(String countryCode) throws Exception;
}
