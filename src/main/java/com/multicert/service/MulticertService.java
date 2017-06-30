package com.multicert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multicert.bean.MulticertResponse;
import com.multicert.manager.IMulticertManager;

@Controller
public class MulticertService {

	@Autowired
	private IMulticertManager multicertManager;

	@RequestMapping(value = "/countryInfo/{countryCode}", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody MulticertResponse getCountryInfo(@PathVariable String countryCode) {
		MulticertResponse response = new MulticertResponse();

		try{
			return this.multicertManager.getCountryInfo(countryCode);
		} catch (Exception e) {
			new MulticertResponse();
		}
		return response;
	}
}