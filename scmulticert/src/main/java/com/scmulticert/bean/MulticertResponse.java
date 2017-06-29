package com.scmulticert.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MulticertResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String lang;
	private String population;
	private String currency;
	private String capital;
	private String icaoCode;

	private List<CityWeather> weatherCityList;

	public static class CityWeather {

		private String stationName;
		private String temperature;
		private String dewPoint;
		private String humidity;
		private String clouds;
		private String weatherCondition;
		private String hectoPascAltimeter;
		private String windDirection;
		private String windSpeed;
		private String lat;
		private String lng;

		public String getStationName() {
			return stationName;
		}
		public void setStationName(String stationName) {
			this.stationName = stationName;
		}
		public String getTemperature() {
			return temperature;
		}
		public void setTemperature(String temperature) {
			this.temperature = temperature;
		}
		public String getDewPoint() {
			return dewPoint;
		}
		public void setDewPoint(String dewPoint) {
			this.dewPoint = dewPoint;
		}
		public String getHumidity() {
			return humidity;
		}
		public void setHumidity(String humidity) {
			this.humidity = humidity;
		}
		public String getClouds() {
			return clouds;
		}
		public void setClouds(String clouds) {
			this.clouds = clouds;
		}
		public String getWeatherCondition() {
			return weatherCondition;
		}
		public void setWeatherCondition(String weatherCondition) {
			this.weatherCondition = weatherCondition;
		}
		public String getHectoPascAltimeter() {
			return hectoPascAltimeter;
		}
		public void setHectoPascAltimeter(String hectoPascAltimeter) {
			this.hectoPascAltimeter = hectoPascAltimeter;
		}
		public String getWindDirection() {
			return windDirection;
		}
		public void setWindDirection(String windDirection) {
			this.windDirection = windDirection;
		}
		public String getWindSpeed() {
			return windSpeed;
		}
		public void setWindSpeed(String windSpeed) {
			this.windSpeed = windSpeed;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
	}

	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getIcaoCode() {
		return icaoCode;
	}
	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}
	public List<CityWeather> getWeatherCityList() {
		return weatherCityList;
	}
	public void setWeatherCityList(List<CityWeather> weatherCityList) {
		this.weatherCityList = weatherCityList;
	}
}
