package com.efe.ms.gateway.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

/**
 * 
 * <p>
 * 因为Gateway里没有配置SwaggerConfig，而运行Swagger-ui又需要依赖一些接口，所以自己建立相应的swagger-
 * resource端点:
 * </p>
 * 
 * @author Liu TianLong 2018年10月4日 上午11:07:04
 */
@RestController
@RequestMapping("/swagger-resources")
public class GatewaySwaggerResourcesHandler {

	@Autowired(required = false)
	private SecurityConfiguration securityConfiguration;

	@Autowired(required = false)
	private UiConfiguration uiConfiguration;

	private final SwaggerResourcesProvider swaggerResourcesProvider;

	public GatewaySwaggerResourcesHandler(
			SwaggerResourcesProvider swaggerResourcesProvider) {
		this.swaggerResourcesProvider = swaggerResourcesProvider;
	}

	@GetMapping("/configuration/security")
	public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
		return Mono
				.just(new ResponseEntity<>(Optional.ofNullable(
						securityConfiguration).orElse(
						SecurityConfigurationBuilder.builder().build()),
						HttpStatus.OK));
	}

    @GetMapping("/configuration/ui")
	public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
		return Mono.just(new ResponseEntity<>(Optional.ofNullable(
				uiConfiguration).orElse(
				UiConfigurationBuilder.builder().build()), HttpStatus.OK));
	}
    
    @SuppressWarnings("rawtypes")
	@GetMapping("")
    public Mono<ResponseEntity> swaggerResources(){
    	return Mono.just(new ResponseEntity<>(swaggerResourcesProvider.get(),HttpStatus.OK));
    }

}
