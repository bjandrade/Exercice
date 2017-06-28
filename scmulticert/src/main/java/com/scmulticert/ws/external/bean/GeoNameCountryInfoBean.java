package com.scmulticert.ws.external.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "geonames")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoNameCountryInfoBean extends AbstractGeoNameBean {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "country")
	private Country country;

	public static class Country {

		private String countryCode;
		private String countryName;
		private String isoNumeric;
		private String isoAlpha3;
		private String fipsCode;
		private String continent;
		private String continentName;
		private String capital;
		private String areaInSqKm;
		private String population;
		private String currencyCode;
		private String languages;
		private String geonameId;
		private String west;
		private String north;
		private String east;
		private String south;
		private String postalCodeFormat;

		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		public String getCountryName() {
			return countryName;
		}
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}
		public String getIsoNumeric() {
			return isoNumeric;
		}
		public void setIsoNumeric(String isoNumeric) {
			this.isoNumeric = isoNumeric;
		}
		public String getIsoAlpha3() {
			return isoAlpha3;
		}
		public void setIsoAlpha3(String isoAlpha3) {
			this.isoAlpha3 = isoAlpha3;
		}
		public String getFipsCode() {
			return fipsCode;
		}
		public void setFipsCode(String fipsCode) {
			this.fipsCode = fipsCode;
		}
		public String getContinent() {
			return continent;
		}
		public void setContinent(String continent) {
			this.continent = continent;
		}
		public String getContinentName() {
			return continentName;
		}
		public void setContinentName(String continentName) {
			this.continentName = continentName;
		}
		public String getCapital() {
			return capital;
		}
		public void setCapital(String capital) {
			this.capital = capital;
		}
		public String getAreaInSqKm() {
			return areaInSqKm;
		}
		public void setAreaInSqKm(String areaInSqKm) {
			this.areaInSqKm = areaInSqKm;
		}
		public String getPopulation() {
			return population;
		}
		public void setPopulation(String population) {
			this.population = population;
		}
		public String getCurrencyCode() {
			return currencyCode;
		}
		public void setCurrencyCode(String currencyCode) {
			this.currencyCode = currencyCode;
		}
		public String getLanguages() {
			return languages;
		}
		public void setLanguages(String languages) {
			this.languages = languages;
		}
		public String getGeonameId() {
			return geonameId;
		}
		public void setGeonameId(String geonameId) {
			this.geonameId = geonameId;
		}
		public String getWest() {
			return west;
		}
		public void setWest(String west) {
			this.west = west;
		}
		public String getNorth() {
			return north;
		}
		public void setNorth(String north) {
			this.north = north;
		}
		public String getEast() {
			return east;
		}
		public void setEast(String east) {
			this.east = east;
		}
		public String getSouth() {
			return south;
		}
		public void setSouth(String south) {
			this.south = south;
		}
		public String getPostalCodeFormat() {
			return postalCodeFormat;
		}
		public void setPostalCodeFormat(String postalCodeFormat) {
			this.postalCodeFormat = postalCodeFormat;
		}
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
