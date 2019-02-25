package com.efe.ms.serviceconsumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Value("${commonConfig}")
	private String commonConfigValue;
	
	@Value("${configValue}")
	private String configValue;
	
	@GetMapping
	public String getAppInfo(){
		return appName;
	}
	
	@GetMapping("/commonConfigValue")
	public String getCommonConfigValue(){
		return commonConfigValue;
	}
	
	@GetMapping("/configValue")
	public String getConfigValue(){
		return configValue;
	}
}
