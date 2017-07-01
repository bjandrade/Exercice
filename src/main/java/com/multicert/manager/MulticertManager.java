package com.multicert.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multicert.bean.MulticertResponse;
import com.multicert.bean.MulticertResponse.CityWeather;
import com.multicert.exception.CitiesNotFoundException;
import com.multicert.exception.CountryNotFoundException;
import com.multicert.ws.external.bean.GeoNameCitiesBean;
import com.multicert.ws.external.bean.GeoNameCitiesBean.City;
import com.multicert.ws.external.bean.GeoNameCountryInfoBean;
import com.multicert.ws.external.bean.GeoNameFindNearByWeatherBean;
import com.multicert.ws.external.bean.ServiceConfigMappingBean;
import com.multicert.ws.external.request.IGeonameRequestManager;

@Service
public class MulticertManager implements IMulticertManager {

	private static final Logger logger = LoggerFactory.getLogger(MulticertManager.class);

	@Autowired
	private IGeonameRequestManager geonameRequest;

	@Override
	public MulticertResponse getCountryInfo(String countryCode) {
		logger.info("getCountryInfo started...");

		MulticertResponse response = new MulticertResponse();

		try{
			// No countryCode
			if(StringUtils.isBlank(countryCode))
				throw new CountryNotFoundException("Empty countryCode");

			// 1----- Begin first request ----//
			ServiceConfigMappingBean mappingBean = new ServiceConfigMappingBean();
			mappingBean.setUsername("bandrade");
			mappingBean.setLang("EN");
			mappingBean.setCountry(countryCode);
			mappingBean.setStyle("full");

			GeoNameCountryInfoBean countryInfoBean = this.geonameRequest.doCountryInfoRequest(mappingBean);

			response.setLang(countryInfoBean.getCountry().getLanguages());
			response.setPopulation(countryInfoBean.getCountry().getPopulation());
			response.setCurrency(countryInfoBean.getCountry().getCurrencyCode());
			response.setCapital(countryInfoBean.getCountry().getCapital());
			response.setIcaoCode("");
			// ----- End first request ----//

			// 2----- Begin second request ----//
			ServiceConfigMappingBean citiesMappingBean = new ServiceConfigMappingBean();
			citiesMappingBean.setUsername("bandrade");
			citiesMappingBean.setCountry(countryCode);
			citiesMappingBean.setNorth(countryInfoBean.getCountry().getNorth());
			citiesMappingBean.setSouth(countryInfoBean.getCountry().getSouth());
			citiesMappingBean.setEast(countryInfoBean.getCountry().getEast());
			citiesMappingBean.setWest(countryInfoBean.getCountry().getWest());

			GeoNameCitiesBean citiesBean = this.geonameRequest.doCitiesRequest(citiesMappingBean);
			// ----- End second request ----//

			// 3----- Begin third request ----//
			List<ServiceConfigMappingBean> mappingBeanList = new ArrayList<>();
			for (Iterator<City> iterator = citiesBean.getCityList().iterator(); iterator.hasNext();) {
				City city = iterator.next();
				if(StringUtils.equals(StringUtils.upperCase(countryCode), city.getCountryCode())){
					ServiceConfigMappingBean nearByWeatherMappingBean = new ServiceConfigMappingBean();
					nearByWeatherMappingBean.setUsername("bandrade");
					nearByWeatherMappingBean.setLat(city.getLat());
					nearByWeatherMappingBean.setLng(city.getLng());
					nearByWeatherMappingBean.setStyle("full");

					mappingBeanList.add(nearByWeatherMappingBean);
				}else{
					iterator.remove();
				}
			}

			List<GeoNameFindNearByWeatherBean> findNearByWeatherListBean = this.geonameRequest.doFindByWeatherRequest(mappingBeanList);

			List<CityWeather> weatherList = new ArrayList<>();
			for(GeoNameFindNearByWeatherBean findNearByWeatherBean : findNearByWeatherListBean){
				CityWeather cityWeather = new CityWeather();
				cityWeather.setStationName(findNearByWeatherBean.getObservation().getStationName());
				cityWeather.setTemperature(findNearByWeatherBean.getObservation().getTemperature());
				cityWeather.setDewPoint(findNearByWeatherBean.getObservation().getDewPoint());
				cityWeather.setHumidity(findNearByWeatherBean.getObservation().getHumidity());
				cityWeather.setClouds(findNearByWeatherBean.getObservation().getClouds());
				cityWeather.setWeatherCondition(findNearByWeatherBean.getObservation().getWeatherCondition());
				cityWeather.setHectoPascAltimeter(findNearByWeatherBean.getObservation().getHectoPascAltimeter());
				cityWeather.setWindDirection(findNearByWeatherBean.getObservation().getWindDirection());
				cityWeather.setWindSpeed(findNearByWeatherBean.getObservation().getWindSpeed());
				cityWeather.setLat(findNearByWeatherBean.getObservation().getLat());
				cityWeather.setLng(findNearByWeatherBean.getObservation().getLng());

				weatherList.add(cityWeather);
			}
			response.setWeatherCityList(weatherList);
			// ----- End third request ----//

		} catch (CountryNotFoundException | CitiesNotFoundException e) {
			logger.debug(e.getMessage(), e);
			return response;
		}

		logger.info("getCountryInfo ended...");
		return response;
	}
}
