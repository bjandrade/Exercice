package com.multicert.ws.external.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.multicert.ws.external.bean.GeoNameCitiesBean;
import com.multicert.ws.external.bean.GeoNameCountryInfoBean;
import com.multicert.ws.external.bean.GeoNameFindNearByWeatherBean;
import com.multicert.ws.external.bean.ServiceConfig;
import com.multicert.ws.external.bean.ServiceConfigMappingBean;
import com.multicert.ws.factory.IServiceConfigType;
import com.multicert.ws.factory.ServiceConfigFactory;
import com.multicert.ws.task.ExternalServiceCaller;
import com.multicert.ws.task.ExternalServiceMapper;

@Service
public class GeonameRequestManager implements IGeonameRequestManager {

	private static final Logger logger = LoggerFactory.getLogger(GeonameRequestManager.class);

	@Override
	public GeoNameCountryInfoBean doCountryInfoRequest(ServiceConfigMappingBean mappingBean) {
		ServiceConfigFactory serviceConfigFactory = ServiceConfigFactory.getServiceConfigFactory();

		ServiceConfig countryConfig = serviceConfigFactory.createServiceConfig(IServiceConfigType.COUNTRY_INFO,  mappingBean);
		ExternalServiceCaller countryInfoService = new ExternalServiceCaller(countryConfig);

		try {
			String countryInfoValue = this.callSingleService(countryInfoService);

			ExternalServiceMapper<GeoNameCountryInfoBean> countryMapper = new ExternalServiceMapper<GeoNameCountryInfoBean>(GeoNameCountryInfoBean.class);
			GeoNameCountryInfoBean countryInfoBean = countryMapper.stringToObject(countryInfoValue);

			return countryInfoBean;
		} catch (InterruptedException | ExecutionException e) {
			logger.error(e.getMessage(), e);
			return new GeoNameCountryInfoBean();
		} catch (JAXBException e) {
			logger.error(e.getMessage(), e);
			return new GeoNameCountryInfoBean();
		}
	}

	@Override
	public GeoNameCitiesBean doCitiesRequest(ServiceConfigMappingBean citiesMappingBean) {
		ServiceConfigFactory serviceConfigFactory = ServiceConfigFactory.getServiceConfigFactory();

		ServiceConfig citiesConfig = serviceConfigFactory.createServiceConfig(IServiceConfigType.CITIES, citiesMappingBean);
		ExternalServiceCaller citiesService = new ExternalServiceCaller(citiesConfig);

		try {
			String citiesValue = this.callSingleService(citiesService);

			ExternalServiceMapper<GeoNameCitiesBean> citiesMapper = new ExternalServiceMapper<GeoNameCitiesBean>(GeoNameCitiesBean.class);
			GeoNameCitiesBean citiesBean = citiesMapper.stringToObject(citiesValue);

			return citiesBean;
		} catch (InterruptedException | ExecutionException e) {
			logger.error(e.getMessage(), e);
			return new GeoNameCitiesBean();
		} catch (JAXBException e) {
			logger.error(e.getMessage(), e);
			return new GeoNameCitiesBean();
		}
	}

	@Override
	public List<GeoNameFindNearByWeatherBean> doFindByWeatherRequest(List<ServiceConfigMappingBean> mappingBeanList) {
		ServiceConfigFactory serviceConfigFactory = ServiceConfigFactory.getServiceConfigFactory();

		List<Callable<String>> findNearByWeatherlist = new ArrayList<>();
		for(ServiceConfigMappingBean mappingBean : mappingBeanList) {
			ServiceConfig nearByWeatherConfig = serviceConfigFactory.createServiceConfig(IServiceConfigType.FIND_NEAR_BY_WEATHER, mappingBean);
			ExternalServiceCaller findNearByWeather = new ExternalServiceCaller(nearByWeatherConfig);

			findNearByWeatherlist.add(findNearByWeather);
		}

		try {
			List<String> weatherCityList = this.callMultipleServices(findNearByWeatherlist);

			ExternalServiceMapper<GeoNameFindNearByWeatherBean> weatherMapper = new ExternalServiceMapper<GeoNameFindNearByWeatherBean>(GeoNameFindNearByWeatherBean.class);
			List<GeoNameFindNearByWeatherBean> findNearByWeatherListBean = new ArrayList<>();
			for(String weatherCity : weatherCityList){
				findNearByWeatherListBean.add(weatherMapper.stringToObject(weatherCity));
			}

			return findNearByWeatherListBean;
		} catch (InterruptedException | ExecutionException e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<>();
		} catch (JAXBException e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<>();
		}
	}


	private String callSingleService(Callable<String> task) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<String> result = executor.submit(task);
		executor.shutdown();

		return result.get();
	}

	private List<String> callMultipleServices(List<Callable<String>> taskList) throws InterruptedException, ExecutionException {
		final Integer THREAD_NUMBER = 5;

		List<String> result = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);
		List<Future<String>> listResult = executor.invokeAll(taskList);

		for(Future<String> future : listResult)
			result.add(future.get());
		executor.shutdown();

		return result;
	}
}
