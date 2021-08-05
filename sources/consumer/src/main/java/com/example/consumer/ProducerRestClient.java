package com.example.consumer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("producer")
public interface ProducerRestClient
{
	@RequestMapping(method = GET,
	                value = "/producer",
	                produces = MediaType.APPLICATION_JSON_VALUE)
	String producer();
}
