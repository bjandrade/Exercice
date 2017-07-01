package com.multicert.ws.external.request;

import java.util.List;

import com.multicert.exception.CitiesNotFoundException;
import com.multicert.exception.CountryNotFoundException;
import com.multicert.ws.external.bean.GeoNameCitiesBean;
import com.multicert.ws.external.bean.GeoNameCountryInfoBean;
import com.multicert.ws.external.bean.GeoNameFindNearByWeatherBean;
import com.multicert.ws.external.bean.ServiceConfigMappingBean;

public interface IGeonameRequestManager {

	public GeoNameCountryInfoBean doCountryInfoRequest(ServiceConfigMappingBean mappingBean) throws CountryNotFoundException;

	public GeoNameCitiesBean doCitiesRequest(ServiceConfigMappingBean citiesMappingBean) throws CitiesNotFoundException;

	public List<GeoNameFindNearByWeatherBean> doFindByWeatherRequest(List<ServiceConfigMappingBean> mappingBeanList);
}
