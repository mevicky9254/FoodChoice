package com.foodchoice.chatgpt.config;

import lombok.Data;

@Data
public class Message {
	
	private String role;
	private String content;
	
	public Message() {
		super();
	}

	public Message(String role, String content) {
		super();
		this.role = role;
		this.content = content;
	}
	
	
	
	
}
