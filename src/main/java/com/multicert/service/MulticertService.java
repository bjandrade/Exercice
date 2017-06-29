package com.multicert.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.multicert.bean.MulticertResponse;
import com.multicert.bean.MulticertResponse.CityWeather;
import com.multicert.ws.external.bean.GeoNameCitiesBean;
import com.multicert.ws.external.bean.GeoNameCitiesBean.City;
import com.multicert.ws.external.bean.GeoNameCountryInfoBean;
import com.multicert.ws.external.bean.GeoNameFindNearByWeatherBean;
import com.multicert.ws.external.bean.ServiceConfig;
import com.multicert.ws.external.bean.ServiceConfigMappingBean;
import com.multicert.ws.factory.IServiceConfigType;
import com.multicert.ws.factory.ServiceConfigFactory;
import com.multicert.ws.task.ExternalServiceCaller;
import com.multicert.ws.task.ExternalServiceMapper;

@Service
public class MulticertService implements IMulticertService {

	private static final Logger logger = LoggerFactory.getLogger(MulticertService.class);

	private static final Integer THREAD_NUMBER = 5;

	@Override
	public MulticertResponse getCountryInfo(String countryCode) throws Exception {
		logger.info("getCountryInfo started...");

		MulticertResponse response = new MulticertResponse();

		try{
			ServiceConfigFactory serviceConfigFactory = ServiceConfigFactory.getServiceConfigFactory();

			ServiceConfigMappingBean mappingBean = new ServiceConfigMappingBean();
			mappingBean.setUsername("bandrade");
			mappingBean.setLang("EN");
			mappingBean.setCountry(countryCode);
			mappingBean.setStyle("full");

			ServiceConfig countryConfig = serviceConfigFactory.createServiceConfig(IServiceConfigType.COUNTRY_INFO,  mappingBean);
			ExternalServiceCaller countryInfoService = new ExternalServiceCaller(countryConfig);

			String countryInfoValue = this.callSingleService(countryInfoService);
//			String countryInfoValue = "<geonames><country><countryCode>PT</countryCode><countryName>Portugal</countryName><isoNumeric>620</isoNumeric><isoAlpha3>PRT</isoAlpha3><fipsCode>PO</fipsCode><continent>EU</continent><continentName>Europa</continentName><capital>Lisboa</capital><areaInSqKm>92391.0</areaInSqKm><population>10676000</population><currencyCode>EUR</currencyCode><languages>pt-PT,mwl</languages><geonameId>2264397</geonameId><west>-9.50052660716588</west><north>42.154311127408</north><east>-6.18915930748288</east><south>36.96125</south><postalCodeFormat>####-###</postalCodeFormat></country></geonames>";

			ExternalServiceMapper<GeoNameCountryInfoBean> countryMapper = new ExternalServiceMapper<GeoNameCountryInfoBean>(GeoNameCountryInfoBean.class);
			GeoNameCountryInfoBean countryInfoBean = countryMapper.stringToObject(countryInfoValue);



			ServiceConfigMappingBean citiesMappingBean = new ServiceConfigMappingBean();
			citiesMappingBean.setUsername("bandrade");
			citiesMappingBean.setNorth(countryInfoBean.getCountry().getNorth());
			citiesMappingBean.setSouth(countryInfoBean.getCountry().getSouth());
			citiesMappingBean.setEast(countryInfoBean.getCountry().getEast());
			citiesMappingBean.setWest(countryInfoBean.getCountry().getWest());

			ServiceConfig citiesConfig = serviceConfigFactory.createServiceConfig(IServiceConfigType.CITIES, citiesMappingBean);
			ExternalServiceCaller citiesService = new ExternalServiceCaller(citiesConfig);

			String citiesValue = this.callSingleService(citiesService);
//			String citiesValue = "<geonames><geoname><toponymName>Lisbon</toponymName><name>Lisbon</name><lat>38.71667</lat><lng>-9.13333</lng><geonameId>2267057</geonameId><countryCode>PT</countryCode><countryName>Portugal</countryName><fcl>P</fcl><fcode>PPLC</fcode></geoname><geoname><toponymName>Porto</toponymName><name>Porto</name><lat>41.14961</lat><lng>-8.61099</lng><geonameId>2735943</geonameId><countryCode>PT</countryCode><countryName>Portugal</countryName><fcl>P</fcl><fcode>PPLA</fcode></geoname><geoname><toponymName>Huelva</toponymName><name>Huelva</name><lat>37.26638</lat><lng>-6.94004</lng><geonameId>2516548</geonameId><countryCode>ES</countryCode><countryName>Spain</countryName><fcl>P</fcl><fcode>PPLA2</fcode></geoname><geoname><toponymName>Badajoz</toponymName><name>Badajoz</name><lat>38.87789</lat><lng>-6.97061</lng><geonameId>2521420</geonameId><countryCode>ES</countryCode><countryName>Spain</countryName><fcl>P</fcl><fcode>PPLA3</fcode></geoname><geoname><toponymName>Braga</toponymName><name>Braga</name><lat>41.55032</lat><lng>-8.42005</lng><geonameId>2742032</geonameId><countryCode>PT</countryCode><countryName>Portugal</countryName><fcl>P</fcl><fcode>PPLA</fcode></geoname><geoname><toponymName>Setúbal</toponymName><name>Setúbal</name><lat>38.5244</lat><lng>-8.8882</lng><geonameId>2262963</geonameId><countryCode>PT</countryCode><countryName>Portugal</countryName><fcl>P</fcl><fcode>PPLA</fcode></geoname><geoname><toponymName>Coimbra</toponymName><name>Coimbra</name><lat>40.20564</lat><lng>-8.41955</lng><geonameId>2740637</geonameId><countryCode>PT</countryCode><countryName>Portugal</countryName><fcl>P</fcl><fcode>PPLA</fcode></geoname><geoname><toponymName>Cáceres</toponymName><name>Cáceres</name><lat>39.47649</lat><lng>-6.37224</lng><geonameId>2520611</geonameId><countryCode>ES</countryCode><countryName>Spain</countryName><fcl>P</fcl><fcode>PPLA2</fcode></geoname><geoname><toponymName>Mérida</toponymName><name>Mérida</name><lat>38.91611</lat><lng>-6.34366</lng><geonameId>2513917</geonameId><countryCode>ES</countryCode><countryName>Spain</countryName><fcl>P</fcl><fcode>PPLA</fcode></geoname><geoname><toponymName>Évora</toponymName><name>Evora</name><lat>38.56667</lat><lng>-7.9</lng><geonameId>2268406</geonameId><countryCode>PT</countryCode><countryName>Portugal</countryName><fcl>P</fcl><fcode>PPLA</fcode></geoname></geonames>";

			ExternalServiceMapper<GeoNameCitiesBean> citiesMapper = new ExternalServiceMapper<GeoNameCitiesBean>(GeoNameCitiesBean.class);
			GeoNameCitiesBean citiesBean = citiesMapper.stringToObject(citiesValue);




			List<Callable<String>> findNearByWeatherlist = new ArrayList<>();
			for (Iterator<City> iterator = citiesBean.getCityList().iterator(); iterator.hasNext();) {
				City city = iterator.next();
				if(StringUtils.equals(StringUtils.upperCase(countryCode), city.getCountryCode())){
					ServiceConfigMappingBean nearBtWeatherMappingBean = new ServiceConfigMappingBean();
					nearBtWeatherMappingBean.setUsername("bandrade");
					nearBtWeatherMappingBean.setLat(city.getLat());
					nearBtWeatherMappingBean.setLng(city.getLng());
					nearBtWeatherMappingBean.setStyle("full");

					ServiceConfig nearByWeatherConfig = serviceConfigFactory.createServiceConfig(IServiceConfigType.FIND_NEAR_BY_WEATHER, nearBtWeatherMappingBean);
					ExternalServiceCaller findNearByWeather = new ExternalServiceCaller(nearByWeatherConfig);
					findNearByWeatherlist.add(findNearByWeather);
				}else{
					iterator.remove();
				}
			}
			List<String> weatherCityList = this.callMultipleServices(findNearByWeatherlist);

			ExternalServiceMapper<GeoNameFindNearByWeatherBean> weatherMapper = new ExternalServiceMapper<GeoNameFindNearByWeatherBean>(GeoNameFindNearByWeatherBean.class);
			List<GeoNameFindNearByWeatherBean> findNearByWeatherListBean = new ArrayList<>();
			for(String weatherCity : weatherCityList){
				findNearByWeatherListBean.add(weatherMapper.stringToObject(weatherCity));
			}

			response.setLang(countryInfoBean.getCountry().getLanguages());
			response.setPopulation(countryInfoBean.getCountry().getPopulation());
			response.setCurrency(countryInfoBean.getCountry().getCurrencyCode());
			response.setCapital(countryInfoBean.getCountry().getCapital());
			response.setIcaoCode("");

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
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			// TODO: handle exception
		}

		logger.info("getCountryInfo ended...");
		return response;
	}

	private String callSingleService(Callable<String> task) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<String> result = executor.submit(task);
		executor.shutdown();

		return result.get();
	}

	private List<String> callMultipleServices(List<Callable<String>> taskList) throws InterruptedException, ExecutionException {
		List<String> result = new ArrayList<>();

		ExecutorService executor = Executors.newFixedThreadPool(MulticertService.THREAD_NUMBER);
		List<Future<String>> listResult = executor.invokeAll(taskList);
//
		for(Future<String> future : listResult)
			result.add(future.get());

		executor.shutdown();
//		result.add("<geonames><observation><observation>LPPT 280900Z 27010KT 250V320 9999 -DZ SCT010 BKN014 20/18 Q1016</observation><observationTime>2017-06-28 09:00:00</observationTime><stationName>Lisboa / Portela</stationName><ICAO>LPPT</ICAO><countryCode>PT</countryCode><elevation>123</elevation><lat>38.766666666666666</lat><lng>-9.116666666666667</lng><temperature>20</temperature><dewPoint>18</dewPoint><humidity>88</humidity><clouds code=\"SCT\">scattered clouds</clouds><weatherCondition code=\"-DZ\">light drizzle</weatherCondition><hectoPascAltimeter>1016.0</hectoPascAltimeter><windDirection>270.0</windDirection><windSpeed>10</windSpeed></observation></geonames>");
//		result.add("<geonames><observation><observation>LPPR 281200Z 31010KT 270V350 9999 FEW023 20/14 Q1015</observation><observationTime>2017-06-28 12:00:00</observationTime><stationName>Porto / Pedras Rubras</stationName><ICAO>LPPR</ICAO><countryCode>PT</countryCode><elevation>73</elevation><lat>41.21666666666667</lat><lng>-8.666666666666666</lng><temperature>20</temperature><dewPoint>14</dewPoint><humidity>68</humidity><clouds code=\"FEW\">few clouds</clouds><weatherCondition>n/a</weatherCondition><hectoPascAltimeter>1015.0</hectoPascAltimeter><windDirection>310.0</windDirection><windSpeed>10</windSpeed></observation></geonames>");
//		result.add("<geonames><observation><observation>LPMT 281100Z 31011KT 9999 SCT024 24/16 Q1016</observation><observationTime>2017-06-28 11:00:00</observationTime><stationName>Montijo Mil.</stationName><ICAO>LPMT</ICAO><countryCode>PT</countryCode><elevation>11</elevation><lat>38.7</lat><lng>-9.05</lng><temperature>24</temperature><dewPoint>16</dewPoint><humidity>60</humidity><clouds code=\"SCT\">scattered clouds</clouds><weatherCondition>n/a</weatherCondition><hectoPascAltimeter>1016.0</hectoPascAltimeter><windDirection>310.0</windDirection><windSpeed>11</windSpeed></observation></geonames>");

		return result;
	}
}
