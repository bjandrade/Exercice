package com.scmulticert.ws.factory;

import java.util.HashMap;
import java.util.Map;

import com.scmulticert.ws.external.bean.ServiceConfig;
import com.scmulticert.ws.external.bean.ServiceConfigMappingBean;

public class ServiceConfigFactory {

	private static ServiceConfigFactory instance;

	private ServiceConfigFactory(){}

	public static ServiceConfigFactory getServiceConfigFactory(){
		if(instance == null){
			synchronized (ServiceConfigFactory.class){
				if(instance == null){
					instance = new ServiceConfigFactory();
					}
				}
			}
		return instance;
	}

	public ServiceConfig createServiceConfig(IServiceConfigType configType, ServiceConfigMappingBean mappingBean){
		if(IServiceConfigType.COUNTRY_INFO.equals(configType)){
			ServiceConfig serviceConfig = new ServiceConfig();

			Map<String, String> params = new HashMap<>();
			params.put("lang", mappingBean.getLang());
			params.put("country", mappingBean.getCountry());
			params.put("username", mappingBean.getUsername());
			params.put("style", mappingBean.getStyle());

			serviceConfig.setHost(configType.getMethod());
			serviceConfig.setParams(params);
			return serviceConfig;
		}else if(IServiceConfigType.CITIES.equals(configType)){
			ServiceConfig serviceConfig = new ServiceConfig();

			Map<String, String> params = new HashMap<>();
			params.put("north", mappingBean.getNorth());
			params.put("south", mappingBean.getSouth());
			params.put("east", mappingBean.getEast());
			params.put("west", mappingBean.getWest());
			params.put("username", mappingBean.getUsername());

			serviceConfig.setHost(configType.getMethod());
			serviceConfig.setParams(params);
			return serviceConfig;
		}else if(IServiceConfigType.FIND_NEAR_BY_WEATHER.equals(configType)){
			ServiceConfig serviceConfig = new ServiceConfig();

			Map<String, String> params = new HashMap<>();
			params.put("lat", mappingBean.getLat());
			params.put("lng", mappingBean.getLng());
			params.put("username", mappingBean.getUsername());

			serviceConfig.setHost(configType.getMethod());
			serviceConfig.setParams(params);
			return serviceConfig;
		}
		return null;
	}
}
