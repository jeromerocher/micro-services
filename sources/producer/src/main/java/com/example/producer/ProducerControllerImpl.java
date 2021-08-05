package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.discovery.EurekaClient;

@RestController
public class ProducerControllerImpl implements ProducerController {

	@Value("${spring.application.name}")
	private String appName;

	@Value("${spring.cloud.discovery.enabled}")
	private Boolean cloudDiscoveryEnable;

	@Autowired
	private EurekaClient eurekaClient;

	@Override
	public String producer() {


		if (cloudDiscoveryEnable) {
			return String.format("Hello : nom ='%s' (avec gateway)!", eurekaClient.getApplication(appName).getName());
		}
		else {

			return String.format("Hello : nom ='%s'! (sans gateway)", appName);
		}
	}
}
