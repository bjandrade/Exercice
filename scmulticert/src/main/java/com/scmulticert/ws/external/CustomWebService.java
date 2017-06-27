package com.scmulticert.ws.external;

import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

public class CustomWebService extends WebService {

	public static ToponymSearchResult countryInfo(String countryCode) {
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
//		searchCriteria.setCountryCode(countryCode);
		
		String url = "/countryInfo?";
		
		if (searchCriteria.getCountryCode() != null) {
			url = url + "&country=" + searchCriteria.getCountryCode();
		}
		
		url = addUserName(url);

//		Element root = connectAndParse(url);
//		searchResult.totalResultsCount = Integer.parseInt(root
//				.getChildText("totalResultsCount"));
//		searchResult.setStyle(Style.valueOf(root.getAttributeValue("style")));
//
//		for (Object obj : root.getChildren("geoname")) {
//			Element toponymElement = (Element) obj;
//			Toponym toponym = getToponymFromElement(toponymElement);
//			toponym.setStyle(searchResult.getStyle());
//			searchResult.toponyms.add(toponym);
//		}
//
//		return searchResult;
		return null;
	}

	private static String addUserName(String url) {
		if ("bandrade" != null) {
			url = url + "&username=" + "bandrade";
		}
		if (null != null) {
			url = url + "&token=" + "";
		}
		return url;
	}
}
