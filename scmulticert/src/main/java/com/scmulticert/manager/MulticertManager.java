package com.scmulticert.manager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geonames.Toponym;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import org.springframework.stereotype.Service;

import com.scmulticert.ws.bean.ServiceConfig;
import com.scmulticert.ws.external.http.HttpConnection;

@Service
public class MulticertManager implements IMulticertManager {

	@Override
	public void getCountryInfo(String countryCode) throws Exception {

		WebService.setUserName("bandrade"); // add your username here
		ToponymSearchResult searchResult = WebService.search("", "PT", "", new String[] {}, 1);
		for (Toponym toponym : searchResult.getToponyms()) {
		     System.out.println(toponym.getName()+" "+ toponym.getCountryName());
		  }

		HttpConnection httpConn = new HttpConnection();
		StringBuilder response = httpConn.sendGet("http://api.geonames.org/countryInfo?lang=pt&country=PT&username=bandrade&style=full");// this.buildUrl(league, i, requestRound).toString());

		System.out.println(response);
		
		
//		ExecutorService executor = Executors.newFixedThreadPool(4);
//
//		Future<GenericResponse> response = executor.submit(new ExternalServiceCaller(this.getCountryInfoConfig(countryCode)));

	}

	private ServiceConfig getCountryInfoConfig(String countryCode) {
		ServiceConfig serviceConfig = new ServiceConfig();

		Map<String, List<String>> params = new HashMap<>();
		params.put("lang", Arrays.asList("pt"));
		params.put("country", Arrays.asList(countryCode));
		params.put("username", Arrays.asList("bandrade"));
		params.put("style", Arrays.asList("full"));

		serviceConfig.setHost("http://api.geonames.org/countryInfo");
		serviceConfig.setParams(params);

		return serviceConfig;
	}
}
