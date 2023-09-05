package com.foodchoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.foodchoice.chatgpt.config.ChatGptRequest;
import com.foodchoice.chatgpt.config.ChatGptResponse;

@RestController
public class ChatBotController {
	
	
	private String model="gpt-3.5-turbo";
	
	
	private String url="https://api.openai.com/v1/chat/completions";
	
	@Autowired
	private RestTemplate template;
	
	
	
	
	@GetMapping("/chat")
	public ResponseEntity<String> getResponseFromOpenApi(@RequestParam("prompt") String prompt){
		
	 ChatGptRequest chatRequest=new ChatGptRequest(model,prompt);
	 ChatGptResponse chatGptResponse=template.postForObject(url, chatRequest, ChatGptResponse.class);
	 String response=chatGptResponse.getChoices().get(0).getMessage().getContent();
	 return new ResponseEntity<>(response,HttpStatus.OK);
	  
	}

}
