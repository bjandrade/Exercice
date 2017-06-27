package com.scmulticert.manager;

import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.springframework.stereotype.Service;

import com.scmulticert.bean.GeoNameCountryInfoBean;
import com.scmulticert.ws.bean.ServiceConfig;
import com.scmulticert.ws.external.http.HttpConnection;

@Service
public class MulticertManager implements IMulticertManager {

	@Override
	public void getCountryInfo(String countryCode) throws Exception {

		HttpConnection httpConn = new HttpConnection();
		StringBuilder response = httpConn.sendGet("http://api.geonames.org/countryInfo?lang=pt&country="+countryCode+"&username=bandrade&style=full");

		Reader reader = new StringReader(response.toString());
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader xmlReader = factory.createXMLStreamReader(reader);
		xmlReader.nextTag();
		xmlReader.nextTag();

		JAXBContext jc = JAXBContext.newInstance(GeoNameCountryInfoBean.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		JAXBElement<GeoNameCountryInfoBean> je = unmarshaller.unmarshal(xmlReader, GeoNameCountryInfoBean.class);

		System.out.println(response);
//		ExecutorService executor = Executors.newFixedThreadPool(4);
//		
//
//		Future<GenericResponse> response = executor.submit(new ExternalServiceCaller(this.getCountryInfoConfig(countryCode)));
//		executor.shutdown();

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
