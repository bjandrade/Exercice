package com.scmulticert.ws.external.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "geonames")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoNameCitiesBean extends AbstractGeoNameBean {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "geoname")
	private List<City> cityList;

	public static class City {

		private String toponymName;
		private String name;
		private String lat;
		private String lng;
		private String geonameId;
		private String countryCode;
		private String countryName;
		private String fcl;
		private String fcode;

		public String getToponymName() {
			return toponymName;
		}
		public void setToponymName(String toponymName) {
			this.toponymName = toponymName;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public String getGeonameId() {
			return geonameId;
		}
		public void setGeonameId(String geonameId) {
			this.geonameId = geonameId;
		}
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
		public String getFcl() {
			return fcl;
		}
		public void setFcl(String fcl) {
			this.fcl = fcl;
		}
		public String getFcode() {
			return fcode;
		}
		public void setFcode(String fcode) {
			this.fcode = fcode;
		}
	}

	public List<City> getCityList() {
		return cityList;
	}
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
}
