package com.foodchoice.chatgpt.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class OpenApiConfig {
	
	private static final String key = "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";
	
	@Bean
	public RestTemplate restTemplate() {
		
		RestTemplate restTemplate= new RestTemplate();
		restTemplate.getInterceptors().add((request, body,execution)->{
			request.getHeaders().add("Authorization", "Bearer "+key);
			return execution.execute(request, body);
		});
		
		return restTemplate;
	}

}
