package com.scmulticert.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scmulticert.bean.MulticertResponse;
import com.scmulticert.manager.IMulticertManager;

@Path("Multicert")
@Component
public class MulticertService {

	@Autowired
	private IMulticertManager multicertManager;

	@GET
	@Path("/CountryInfo/{countryCode}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public MulticertResponse getCountryInfo(@PathParam("countryCode") String countryCode) {
		MulticertResponse response = new MulticertResponse();

		try{
			response = this.multicertManager.getCountryInfo(countryCode);

			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
