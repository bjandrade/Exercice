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
import com.multicert.service.IMulticertService;

@Controller
public class MulticertController {

	private static final Logger logger = LoggerFactory.getLogger(MulticertController.class);

	@Autowired
	private IMulticertService service;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String loginPage(Locale locale, Model model) {
		logger.info("/index visited.");

		return "index";
	}

	@RequestMapping(value = "/getInfo", method = RequestMethod.POST)
	public String login(@Validated CountryDetail countryDetail, Model model) {
		logger.info("/getInfo visited.");

		MulticertResponse response = new MulticertResponse();
		try {
			response = this.service.getCountryInfo(countryDetail.getCountryCode());
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("countryInfo", response);
		return "countryInfo";
	}
}
