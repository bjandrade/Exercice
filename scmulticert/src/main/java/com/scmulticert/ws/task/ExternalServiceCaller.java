package com.scmulticert.ws.task;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import com.scmulticert.ws.bean.GenericResponse;
import com.scmulticert.ws.bean.ServiceConfig;

public class ExternalServiceCaller implements Callable<GenericResponse>{

	private ServiceConfig serviceConfig;

	public ExternalServiceCaller(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}

	@Override
	public GenericResponse call() throws Exception {

		WebService.setUserName("bandrade"); // add your username here
		ToponymSearchResult searchResult = WebService.search("", "PT", "", new String[] {}, 1);
		for (Toponym toponym : searchResult.getToponyms()) {
		     System.out.println(toponym.getName()+" "+ toponym.getCountryName());
		  }
		
//		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
//		  searchCriteria.setQ("zurich");
//		  ToponymSearchResult searchResult = WebService.search(searchCriteria);
//		  for (Toponym toponym : searchResult.getToponyms()) {
//		     System.out.println(toponym.getName()+" "+ toponym.getCountryName());
//		  }
		
//		WebTarget target = this.getService(GENERIC_REST_URL + "/Configuration/Autocomplete/Country/List");
//		Response response = target.request(MediaType.APPLICATION_JSON).buildGet().invoke();
//		AutocompleteResponse restResponse = response.readEntity(AutocompleteResponse.class);
//
//		if(restResponse == null)
//			throw new PredictionsIntegrationException("Error communicating with REST service!");
//
//		return restResponse.getList();

		
		
		return null;
	}

	private String constructParams(Map<String, List<String>> params) {
		return null;
	}
}
