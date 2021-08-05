package com.example.serviceregistrationanddiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistrationAndDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistrationAndDiscoveryServiceApplication.class, args);
	}

	/*@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuild builder) {
		return builder.routes()
		              .route(route->route.path("/client-test/**")).uri("lb://client-test").id("r1")
		              .build();
	}*/
	// tag::route-locator[]
	/*@Bean
	public DiscoveryClient myRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp ) {
		String httpUri = uriConfiguration.getHttpbin();
		return builder.routes()
		              .route(p -> p
			              .path("/get")
			              .filters(f -> f.addRequestHeader("Hello", "World"))
			              .uri(httpUri))
		              .route(p -> p
			              .host("*.hystrix.com")
			              .filters(f -> f
				              .hystrix(config -> config
					              .setName("mycmd")
					              .setFallbackUri("forward:/fallback")))
			              .uri(httpUri))
		              .build();
	}*/
	// end::route-locator[]

}
