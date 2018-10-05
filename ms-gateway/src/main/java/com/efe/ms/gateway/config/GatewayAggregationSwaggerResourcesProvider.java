package com.efe.ms.gateway.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * 
 * <p>
 * spring cloud gateway 服务文档聚合:
 * </p>
 * 
 * @author Liu TianLong 2018年10月4日 上午10:32:57
 */
@Component
@Primary
public class GatewayAggregationSwaggerResourcesProvider implements
		SwaggerResourcesProvider {

	public static final String SWAGGER_DOCS_PATH = "v2/api-docs";
	private static final String REPLACE_PATTERN = "\\*\\*";
	private static final String SWAGGER_VERSION = "2.0";
	private static final Pattern IGNORE_PATH_PATTERN = Pattern
			.compile("config-server|\\w*(-route)$",Pattern.CASE_INSENSITIVE);
	private static final Pattern AGGREATION_PATH_PATTERN = Pattern
			.compile("\\w*(-service)$",Pattern.CASE_INSENSITIVE);

	private final RouteLocator routeLocator;
	private final GatewayProperties gatewayProperties;

	@Autowired
	private DiscoveryClient discoveryClient;

	public GatewayAggregationSwaggerResourcesProvider(
			RouteLocator routeLocator, GatewayProperties gatewayProperties) {
		this.routeLocator = routeLocator;
		this.gatewayProperties = gatewayProperties;
	}

	/*
	 * @Override public List<SwaggerResource> get() { List<SwaggerResource>
	 * resources = new ArrayList<SwaggerResource>(); List<Route> routes = new
	 * ArrayList<Route>(); routeLocator.getRoutes().subscribe(route -> {
	 * route.getPredicate(). routes.add(route); System.out.println(route); });
	 * // routes.stream().filter(route ->
	 * AGGREATION_PATH_PATTERN.matcher(route.getId()).matches()) //
	 * .forEach(route -> route.getPredicate().); return resources; }
	 */

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<SwaggerResource>();
		Optional.ofNullable(discoveryClient.getServices())
				.get()
				.stream()
				.filter(serviceName -> AGGREATION_PATH_PATTERN.matcher(
						serviceName).matches())
				.forEach(
						serviceName -> resources.add(swaggerResources(
								serviceName, "/" + serviceName + "/"
										+ SWAGGER_DOCS_PATH, SWAGGER_VERSION)));
		/*
		 * List<String> routes = new ArrayList<String>();
		 * routeLocator.getRoutes().subscribe(route ->
		 * routes.add(route.getId())); // 获取所有的路由信息 gatewayProperties
		 * .getRoutes() .stream() .filter(route ->
		 * routes.contains(route.getId())) .forEach( route -> route
		 * .getPredicates() .stream() .filter(predicate -> "Path"
		 * .equalsIgnoreCase(predicate.getName())) .forEach( predicate ->
		 * resources.add(swaggerResources( route.getId(), predicate .getArgs()
		 * .get(NameUtils.GENERATED_NAME_PREFIX + "0") .replaceAll(
		 * REPLACE_PATTERN, SWAGGER_DOCS_PATH), SWAGGER_VERSION))));
		 */
		return resources;
	}

	private SwaggerResource swaggerResources(String name, String location,
			String version) {
		SwaggerResource resource = new SwaggerResource();
		resource.setName(name);
		resource.setLocation(location);
		resource.setSwaggerVersion(version);
		return resource;
	}
}
