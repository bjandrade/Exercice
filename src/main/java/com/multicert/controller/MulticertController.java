package com.multicert.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.multicert.bean.CountryDetail;
import com.multicert.bean.MulticertResponse;
import com.multicert.manager.IMulticertManager;

@Controller
public class MulticertController {

	private static final Logger logger = LoggerFactory.getLogger(MulticertController.class);

	@Autowired
	private IMulticertManager service;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String loginPage(Locale locale, Model model) {
		logger.info("/index visited.");

		return "index";
	}

	@RequestMapping(value = "/getInfo", method = {RequestMethod.POST, RequestMethod.GET})
	public String login(@Validated CountryDetail countryDetail, Model model) {
		logger.info("/getInfo visited.");

		MulticertResponse response = null;
		try {
			response = this.service.getCountryInfo(countryDetail.getCountryCode());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response = new MulticertResponse();
		}

		model.addAttribute("countryInfo", response);
		return "countryInfo";
	}
}
