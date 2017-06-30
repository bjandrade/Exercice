package com.multicert.ws.task;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ExternalServiceMapper<T> {

	final Class<T> typeParameterClass;

	public ExternalServiceMapper(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}

	@SuppressWarnings("unchecked")
	public T stringToObject(String xmlValue) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(typeParameterClass);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlValue);
		T findNearWeatherBean = (T) unmarshaller.unmarshal(reader);

		return findNearWeatherBean;
	}
}
