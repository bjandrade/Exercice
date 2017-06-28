package com.scmulticert.ws.task;

import java.io.IOException;
import java.util.concurrent.Callable;

import com.scmulticert.ws.external.bean.ServiceConfig;
import com.scmulticert.ws.external.http.HttpConnection;

public class ExternalServiceCaller implements Callable<String>{

	private static final String GEONAME_URL = "http://api.geonames.org/";
	private ServiceConfig serviceConfig;

	public ExternalServiceCaller(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}

	@Override
	public String call() throws IOException {

		HttpConnection httpConn = new HttpConnection();
		StringBuilder response = httpConn.sendGet(this.buildUrl());

//		JAXBContext jaxbContext = JAXBContext.newInstance(GeoNameCountryInfoBean.class);
//		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//
//		StringReader reader = new StringReader(response.toString());
//		GeoNameCountryInfoBean countryInfoBean = (GeoNameCountryInfoBean) unmarshaller.unmarshal(reader);

//		Reader reader = new StringReader(response.toString());
//		XMLInputFactory factory = XMLInputFactory.newInstance();
//		XMLStreamReader xmlReader = factory.createXMLStreamReader(reader);
//		xmlReader.nextTag();
//		xmlReader.nextTag();

//		JAXBContext jc = JAXBContext.newInstance(GeoNameCountryInfoBean.class);
//		Unmarshaller unmarshaller = jc.createUnmarshaller();
//		JAXBElement<GeoNameCountryInfoBean> je = unmarshaller.unmarshal(xmlReader, GeoNameCountryInfoBean.class);
//
		System.out.println(response);

//		return je.getValue();
//		return countryInfoBean;
		return response.toString();
	}


	private String buildUrl(){
		StringBuilder url = new StringBuilder(ExternalServiceCaller.GEONAME_URL);

		url.append("/" + this.serviceConfig.getHost());

		String separator = "?";
		for(String key : this.serviceConfig.getParams().keySet()){
			url.append(separator + key + "=" + this.serviceConfig.getParams().get(key));
			separator = "&";
		}
		return url.toString();
	}
}
