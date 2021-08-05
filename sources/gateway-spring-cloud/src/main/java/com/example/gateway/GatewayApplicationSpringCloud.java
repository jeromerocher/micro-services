package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplicationSpringCloud
{

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplicationSpringCloud.class, args);
	}

	@Bean
	public ServerCodecConfigurer serverCodecConfigurer() {
		return ServerCodecConfigurer.create();
	}

}
