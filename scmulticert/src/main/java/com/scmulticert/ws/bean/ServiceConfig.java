package com.scmulticert.ws.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ServiceConfig implements Serializable{

	private static final long serialVersionUID = 1L;

	private String host;
	private Map<String, List<String>> params;

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Map<String, List<String>> getParams() {
		return params;
	}
	public void setParams(Map<String, List<String>> params) {
		this.params = params;
	}
}
