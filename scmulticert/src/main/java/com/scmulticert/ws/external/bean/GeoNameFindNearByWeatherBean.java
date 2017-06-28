package com.scmulticert.ws.external.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "geonames")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoNameFindNearByWeatherBean extends AbstractGeoNameBean {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "observation")
	private List<Observation> observationList;

	public static class Observation {

		private String observationTime;
		private String stationName;
		private String ICAO;
		private String countryCode;
		private String elevation;
		private String lat;
		private String lng;
		private String temperature;
		private String dewPoint;
		private String humidity;
		private String clouds;
		private String weatherCondition;
		private String hectoPascAltimeter;
		private String windDirection;
		private String windSpeed;

		public String getObservationTime() {
			return observationTime;
		}
		public void setObservationTime(String observationTime) {
			this.observationTime = observationTime;
		}
		public String getStationName() {
			return stationName;
		}
		public void setStationName(String stationName) {
			this.stationName = stationName;
		}
		public String getICAO() {
			return ICAO;
		}
		public void setICAO(String iCAO) {
			ICAO = iCAO;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		public String getElevation() {
			return elevation;
		}
		public void setElevation(String elevation) {
			this.elevation = elevation;
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
	}

	public List<Observation> getObservationList() {
		return observationList;
	}
	public void setObservationList(List<Observation> observationList) {
		this.observationList = observationList;
	}
}
