package com.example.producer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

public interface ProducerController {

	@RequestMapping(method = GET,
	                value = "/{spring.application.name}",
	                produces = MediaType.APPLICATION_JSON_VALUE)
	String producer();
}
