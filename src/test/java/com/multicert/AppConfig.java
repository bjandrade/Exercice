package com.multicert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.multicert.manager.IMulticertManager;
import com.multicert.manager.MulticertManager;

@Configuration
public class AppConfig {
	@Bean
	public IMulticertManager getMulticertManager() {
		return new MulticertManager();
	}
}