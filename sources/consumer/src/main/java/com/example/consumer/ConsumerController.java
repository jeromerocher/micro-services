package com.example.consumer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

public interface ConsumerController {

	@RequestMapping(method = GET,
	                value = "/{spring.application.name}",
	                produces = MediaType.APPLICATION_JSON_VALUE)
	String consumer();
}
