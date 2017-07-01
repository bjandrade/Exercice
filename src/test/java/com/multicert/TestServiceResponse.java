package com.multicert;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.multicert.bean.MulticertResponse;
import com.multicert.manager.IMulticertManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/servlet-context.xml", "/root-context.xml"})
public class TestServiceResponse {

	@Autowired
	private IMulticertManager multicertManager;

	@Test
	public void testEmptyRequest() {
		try {
			MulticertResponse response = this.multicertManager.getCountryInfo("");
			Assert.assertNull(response.getCapital());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testWrongCountryCode() {
		try {
			MulticertResponse response = this.multicertManager.getCountryInfo("noCoutry");
			Assert.assertNull(response.getCapital());
			Assert.assertNull(response.getCurrency());
			Assert.assertNull(response.getIcaoCode());
			Assert.assertNull(response.getLang());
			Assert.assertNull(response.getPopulation());
			Assert.assertNull(response.getWeatherCityList());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCorrectCountryCode() {
		try {
			MulticertResponse response = this.multicertManager.getCountryInfo("PT");
			Assert.assertNotNull(response.getCapital());
			Assert.assertNotNull(response.getCurrency());
			Assert.assertNotNull(response.getIcaoCode());
			Assert.assertNotNull(response.getLang());
			Assert.assertNotNull(response.getPopulation());
			Assert.assertNotNull(response.getWeatherCityList());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}
