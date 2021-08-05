package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerControllerImpl implements ConsumerController
{
	@Autowired
	private ConsumerService consumerService;

	@Override
	public String consumer() {
		return consumerService.traitement();
	}


}
