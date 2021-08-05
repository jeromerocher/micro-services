package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ProducerRestClient producerRestClient;

	@HystrixCommand(fallbackMethod = "defaultConsumer")
	public String traitement () {
		return String.format("Appel du producer qui retourne : '%s'", producerRestClient.producer());
	}

	public String defaultConsumer () {
		return "Producer non trouve, appel de la fallback";
	}
}
